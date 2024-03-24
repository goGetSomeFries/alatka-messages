package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TV域解析器
 * 参考F62域usage=IO
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class TVDomainParsed extends AbstractDomainParsed {

    private static final int TAG_LENGTH = 3;

    @Override
    public int getOrder() {
        return 90;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return MessageDefinition.Type.iso == messageDefinition.getType() &&
                messageDefinition.getDomainType() == MessageDefinition.DomainType.TV;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        byte[] tagBytes = ((IsoFieldDefinition) fieldDefinition).getAliasName().getBytes();
        byte[] valueBytes = this.padding(bytes, fieldDefinition.getLength(), fieldDefinition.getFieldType(), fieldDefinition);
        return BytesUtil.concat(tagBytes, valueBytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        return Arrays.copyOfRange(bytes, counter.addAndGet(TAG_LENGTH), counter.addAndGet(fieldDefinition.getLength()));
    }

}
