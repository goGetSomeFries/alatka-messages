package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public abstract class FixedTLVDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 70;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        if (bytes.length == 0) {
            return bytes;
        }
        String aliasName = ((IsoFieldDefinition) fieldDefinition).getAliasName();
        byte[] tagBytes = buildTagBytes(aliasName);
        String valueLength = String.format("%0" + lenLength() + "d", bytes.length);
        byte[] lenBytes = buildLenBytes(valueLength);

        return BytesUtil.concat(tagBytes, lenBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {

        byte[] lenBytes = Arrays.copyOfRange(bytes, counter.addAndGet(tagLength()), counter.addAndGet(lenLength()));
        int valueLength = buildLen(lenBytes);

        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(valueLength));
    }

    protected abstract int tagLength();

    protected abstract int lenLength();

    protected abstract byte[] buildTagBytes(String tag);

    protected abstract byte[] buildLenBytes(String len);

    protected abstract int buildLen(byte[] lenBytes);

}
