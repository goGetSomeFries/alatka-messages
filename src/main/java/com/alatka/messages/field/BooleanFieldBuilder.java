package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * {@link Boolean}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BooleanFieldBuilder extends AbstractFieldBuilder<Boolean> {

    private final IntegerFieldBuilder fieldBuilder = new IntegerFieldBuilder();

    @Override
    protected byte[] fromObjectToAscii(Boolean value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToAscii(this.convert(value), fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBinary(Boolean value, FieldDefinition fieldDefinition) {
        return fieldBuilder.fromObjectToBinary(this.convert(value), fieldDefinition);
    }

    @Override
    protected byte[] fromObjectToBcd(Boolean value, FieldDefinition definition) {
        return fieldBuilder.fromObjectToBcd(this.convert(value), definition);
    }

    @Override
    protected byte[] fromObjectToEbcdic(Boolean value, FieldDefinition definition) {
        return fieldBuilder.fromObjectToEbcdic(this.convert(value), definition);
    }

    @Override
    protected Boolean toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        Integer value = fieldBuilder.toObjectWithAscii(bytes, fieldDefinition);
        return this.convert(value);
    }

    @Override
    protected Boolean toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        Integer value = fieldBuilder.toObjectWithBinary(bytes, fieldDefinition);
        return this.convert(value);
    }

    @Override
    protected Boolean toObjectWithBcd(byte[] bytes, FieldDefinition definition) {
        Integer value = fieldBuilder.toObjectWithBcd(bytes, definition);
        return this.convert(value);
    }

    @Override
    protected Boolean toObjectWithEbcdic(byte[] bytes, FieldDefinition definition) {
        Integer value = fieldBuilder.toObjectWithEbcdic(bytes, definition);
        return this.convert(value);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == Boolean.class;
    }

    private int convert(Boolean value) {
        return value ? 1 : 0;
    }

    private Boolean convert(Integer value) {
        return value.equals(1);
    }

}
