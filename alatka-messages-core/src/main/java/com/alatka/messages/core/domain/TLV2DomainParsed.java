package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class TLV2DomainParsed extends AbstractDomainParsed {
    private static final int TAG_LENGTH = 3;
    private static final int LEN_LENGTH = 3;

    @Override
    public int getOrder() {
        return 70;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return MessageDefinition.Type.iso == messageDefinition.getType() &&
                messageDefinition.getDomainType() == MessageDefinition.DomainType.TLV2;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        if (bytes.length == 0) {
            return bytes;
        }
        byte[] tagBytes = ((IsoFieldDefinition) fieldDefinition).getAliasName().getBytes();
        byte[] valueBytes = bytes;
        byte[] lenBytes = String.format("%0" + LEN_LENGTH + "d", valueBytes.length).getBytes();

        return BytesUtil.concat(tagBytes, lenBytes, valueBytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {

        byte[] lenBytes = Arrays.copyOfRange(bytes, counter.addAndGet(TAG_LENGTH), counter.addAndGet(LEN_LENGTH));
        int valueLength = Integer.parseInt(new String(lenBytes));

        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(valueLength));
    }

}
