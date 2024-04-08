package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不处理域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class RawDomainParsed extends AbstractDomainParsed {

    private AbstractDomainParsed fixedDomainParsed = new FixedDomainParsed();

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return fieldDefinition.getStatus() == FieldDefinition.Status.NO_PARSE;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        if (fieldDefinition.getFixed()) {
            return fixedDomainParsed.unpack(bytes, fieldDefinition, counter);
        }

        Integer length = fieldDefinition.getLength();
        byte[] lengthBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
        byte[] valueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.bytesToInt(lengthBytes, fieldDefinition)));

        if (valueBytes.length > ((IsoFieldDefinition) fieldDefinition).getMaxLength()) {
            throw new RuntimeException("fieldDefinition: " + fieldDefinition
                    + " max length: " + ((IsoFieldDefinition) fieldDefinition).getMaxLength() + ", actually length: " + valueBytes.length);
        }
        return BytesUtil.concat(lengthBytes, valueBytes);
    }

    private int bytesToInt(byte[] lengthBytes, FieldDefinition fieldDefinition) {
        if (fieldDefinition.getParseType().getLenParseType() == FieldDefinition.ParseType.LPT.A) {
            return Integer.parseInt(new String(lengthBytes));

        }
        int length = BytesUtil.bytesToInt(lengthBytes);
        return fieldDefinition.getParseType() != FieldDefinition.ParseType.BCD ?
                length :
                length % 2 == 0 ? length / 2 : length / 2 + 1;
    }
}
