package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 位图域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class BitmapDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return MessageDefinition.Type.iso == messageDefinition.getType()
                && MessageDefinition.Kind.payload == messageDefinition.getKind()
                && fieldDefinition.getDomainNo() == 1
                && Map.class.isAssignableFrom(fieldDefinition.getClassType());
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        byte[] flag = Arrays.copyOfRange(bytes, counter.get(), counter.get() + 1);
        int length = (flag[0] & 0x80) == 0 ? 8 : 16;
        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(length));
    }
}
