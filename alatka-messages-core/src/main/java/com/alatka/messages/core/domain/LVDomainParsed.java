package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

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
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return !fieldDefinition.getFixed() && MessageDefinition.Type.iso == messageDefinition.getType();
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        if (this.raw(fieldDefinition)) {
            return bytes;
        }
        byte[] lengthBytes = this.intToBytes(bytes.length, fieldDefinition);
        return BytesUtil.concat(lengthBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        Integer length = fieldDefinition.getLength();
        byte[] lengthBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
        byte[] valueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.bytesToInt(lengthBytes, fieldDefinition)));

        if (valueBytes.length > ((IsoFieldDefinition) fieldDefinition).getMaxLength()) {
            throw new IllegalArgumentException("fieldDefinition: " + fieldDefinition
                    + " max length: " + ((IsoFieldDefinition) fieldDefinition).getMaxLength() + ", actually length: " + valueBytes.length);
        }
        return raw(fieldDefinition) ? BytesUtil.concat(lengthBytes, valueBytes) : valueBytes;
    }

    protected boolean raw(FieldDefinition fieldDefinition) {
        return fieldDefinition.getParseType() == FieldDefinition.ParseType.BCD
                && fieldDefinition.getClassType() == String.class;
    }

    /**
     * L值 int -> bytes
     *
     * @param length          L值(int)
     * @param fieldDefinition {@link FieldDefinition}
     * @return L值(bytes)
     */
    protected abstract byte[] intToBytes(int length, FieldDefinition fieldDefinition);

    /**
     * L值 bytes -> int
     *
     * @param lengthBytes     L值(bytes)
     * @param fieldDefinition {@link FieldDefinition}
     * @return L值(int)
     */
    protected abstract int bytesToInt(byte[] lengthBytes, FieldDefinition fieldDefinition);
}
