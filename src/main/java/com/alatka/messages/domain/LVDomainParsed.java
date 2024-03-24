package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LV（length-value）域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class LVDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return !fieldDefinition.getFixed() && MessageDefinition.Type.iso == messageDefinition.getType();
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        byte[] lengthBytes = this.padding(
                String.valueOf(bytes.length).getBytes(),
                fieldDefinition.getLength(),
                FieldDefinition.FieldType.NUMBER,
                fieldDefinition);
        return BytesUtil.concat(lengthBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        Integer length = fieldDefinition.getLength();
        byte[] lengthBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
        byte[] valueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(Integer.parseInt(new String(lengthBytes))));
        if (valueBytes.length > ((IsoFieldDefinition) fieldDefinition).getMaxLength()) {
            throw new RuntimeException("fieldDefinition: " + fieldDefinition + ", actually length: " + valueBytes.length);
        }
        return valueBytes;
    }
}
