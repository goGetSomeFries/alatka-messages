package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.Arrays;
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
                && fieldDefinition.getDomainNo() == 1;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        int offset = this.calculateOffset(bytes, new AtomicInteger(counter.intValue()), counter.intValue());
        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(offset));
    }

    private int calculateOffset(byte[] bytes, AtomicInteger counter, int startOffset) {
        byte[] flag = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(8));
        if ((flag[0] & 0x80) == 0) {
            return counter.addAndGet(-startOffset);
        }
        return this.calculateOffset(bytes, counter, startOffset);
    }

}
