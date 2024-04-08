package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * 原始报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class RawFieldBuilder extends AbstractFieldBuilder<byte[]> {

    @Override
    protected byte[] fromObjectToBcd(byte[] value, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.toObjectWithNone(bytes, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToNone(byte[] value, FieldDefinition fieldDefinition) {
        return value;
    }

    @Override
    protected byte[] toObjectWithNone(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getStatus() == FieldDefinition.Status.NO_PARSE;
    }
}
