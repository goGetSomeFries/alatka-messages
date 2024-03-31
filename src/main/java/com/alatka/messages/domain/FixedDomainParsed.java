package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定长域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class FixedDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return fieldDefinition.getFixed();
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.padding(bytes, fieldDefinition);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        Integer length = fieldDefinition.getLength();
        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
    }
}
