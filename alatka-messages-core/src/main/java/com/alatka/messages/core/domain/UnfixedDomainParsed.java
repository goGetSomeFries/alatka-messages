package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不定长域解析器<br>
 * e.g.cups F122.2域
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class UnfixedDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 40;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return !fieldDefinition.getFixed()
                && MessageDefinition.Type.iso == messageDefinition.getType()
                && fieldDefinition.getLength() == -1;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        int length = bytes.length - counter.get();
        if (length <= 0) {
            throw new IllegalArgumentException("域长度配置错误");
        }
        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
    }
}
