package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

/**
 * 原始报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class RawFieldBuilder extends AbstractFieldBuilder<byte[]> {

    private final BytesFieldBuilder fieldBuilder = new BytesFieldBuilder();

    @Override
    protected byte[] fromObjectToAscii(byte[] value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToAscii(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBinary(byte[] value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToBinary(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBcd(byte[] value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToBcd(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToEbcdic(byte[] value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToEbcdic(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToNone(byte[] value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return fieldBuilder.toObjectWithAscii(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        return fieldBuilder.toObjectWithBinary(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return fieldBuilder.toObjectWithBcd(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        return fieldBuilder.toObjectWithEbcdic(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithNone(byte[] bytes, FieldDefinition fieldDefinition) {
        return fieldBuilder.toObjectWithNone(bytes, fieldDefinition);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 1000;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getStatus() == FieldDefinition.Status.RAW;
    }
}
