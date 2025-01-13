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
        int length = fieldDefinition.getParseType() == FieldDefinition.ParseType.BCD ? bytes.length * 2 : bytes.length;
        byte[] lengthBytes = this.intToBytes(length, (IsoFieldDefinition) fieldDefinition);
        return BytesUtil.concat(lengthBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        byte[] lengthBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(fieldDefinition.getLength()));
        int length = this.bytesToInt(lengthBytes, (IsoFieldDefinition) fieldDefinition);
        length = fieldDefinition.getParseType() != FieldDefinition.ParseType.BCD ? length :
                length % 2 == 0 ? length / 2 : length / 2 + 1;
        byte[] valueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));

        if (valueBytes.length > ((IsoFieldDefinition) fieldDefinition).getMaxLength()) {
            throw new IllegalArgumentException("fieldDefinition: " + fieldDefinition
                    + " max length: " + ((IsoFieldDefinition) fieldDefinition).getMaxLength() + ", actually length: " + valueBytes.length);
        }
        return this.raw(fieldDefinition) ? BytesUtil.concat(lengthBytes, valueBytes) : valueBytes;
    }

    protected boolean raw(FieldDefinition fieldDefinition) {
        return fieldDefinition.getParseType() == FieldDefinition.ParseType.BCD
                && fieldDefinition.getClassType() == String.class;
    }

    protected FieldDefinition.ParseType buildLenParseType(FieldDefinition fieldDefinition) {
        IsoFieldDefinition definition = (IsoFieldDefinition) fieldDefinition;
        FieldDefinition.ParseType lenParseType = definition.getLenParseType();
        if (lenParseType != null) {
            return lenParseType;
        }
        return definition.getParseType() == FieldDefinition.ParseType.NONE ?
                FieldDefinition.ParseType.ASCII : definition.getParseType();
    }

    /**
     * L值 int -> bytes
     *
     * @param length          L值(int)
     * @param fieldDefinition {@link FieldDefinition}
     * @return L值(bytes)
     */
    protected abstract byte[] intToBytes(int length, IsoFieldDefinition fieldDefinition);

    /**
     * L值 bytes -> int
     *
     * @param lengthBytes     L值(bytes)
     * @param fieldDefinition {@link FieldDefinition}
     * @return L值(int)
     */
    protected abstract int bytesToInt(byte[] lengthBytes, IsoFieldDefinition fieldDefinition);
}
