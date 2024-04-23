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

    private AbstractDomainParsed fixedDomainParsed = new FixedDomainParsed();

    private AbstractDomainParsed asciiLVDomainParsed = new AsciiLVDomainParsed() {
        @Override
        protected boolean raw(FieldDefinition fieldDefinition) {
            return true;
        }
    };

    private AbstractDomainParsed binaryLVDomainParsed = new BinaryLVDomainParsed() {
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
        return fieldDefinition.getStatus() == FieldDefinition.Status.NO_PARSE;
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
