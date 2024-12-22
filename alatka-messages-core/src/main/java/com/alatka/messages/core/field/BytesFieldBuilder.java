package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

/**
 * byte[]类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BytesFieldBuilder extends AbstractFieldBuilder<byte[]> {

    @Override
    protected byte[] fromObjectToAscii(byte[] value, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBinary(byte[] value, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBcd(byte[] value, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToEbcdic(byte[] value, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(value, fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToNone(byte[] value, FieldDefinition fieldDefinition) {
        return value;
    }

    @Override
    protected byte[] toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.fromObjectToNone(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.toObjectWithNone(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.toObjectWithNone(bytes, fieldDefinition);
    }

    @Override
    protected byte[] toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.toObjectWithNone(bytes, fieldDefinition);
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
        return definition.getClassType() == byte[].class;
    }
}
