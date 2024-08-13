package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不处理域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class RawDomainParsed extends AbstractDomainParsed {

    private final AbstractDomainParsed fixedDomainParsed = new FixedDomainParsed();

    private final AbstractDomainParsed asciiLVDomainParsed = new AsciiLVDomainParsed() {
        @Override
        protected boolean raw(FieldDefinition fieldDefinition) {
            return true;
        }
    };

    private final AbstractDomainParsed binaryLVDomainParsed = new BinaryLVDomainParsed() {
        @Override
        protected boolean raw(FieldDefinition fieldDefinition) {
            return true;
        }
    };

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return fieldDefinition.getStatus() == FieldDefinition.Status.RAW;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        if (fieldDefinition.getFixed()) {
            return fixedDomainParsed.unpack(bytes, fieldDefinition, counter);
        }

        return fieldDefinition.getParseType().getLenParseType() == FieldDefinition.ParseType.LPT.A ?
                asciiLVDomainParsed.unpack(bytes, fieldDefinition, counter) :
                binaryLVDomainParsed.unpack(bytes, fieldDefinition, counter);
    }

}
