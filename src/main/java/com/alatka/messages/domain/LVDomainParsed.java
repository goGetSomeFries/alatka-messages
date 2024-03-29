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
public abstract class LVDomainParsed extends AbstractDomainParsed {

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
        byte[] lengthBytes = this.intToBytes(bytes.length, fieldDefinition);
        return BytesUtil.concat(lengthBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        Integer length = fieldDefinition.getLength();
        byte[] lengthBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
        byte[] valueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.bytesToInt(lengthBytes)));
        if (valueBytes.length > ((IsoFieldDefinition) fieldDefinition).getMaxLength()) {
            throw new RuntimeException("fieldDefinition: " + fieldDefinition
                    + " max length: " + ((IsoFieldDefinition) fieldDefinition).getMaxLength() + ", actually length: " + valueBytes.length);
        }
        return valueBytes;
    }

    protected abstract byte[] intToBytes(int length, FieldDefinition fieldDefinition);

    protected abstract int bytesToInt(byte[] lengthBytes);
}
