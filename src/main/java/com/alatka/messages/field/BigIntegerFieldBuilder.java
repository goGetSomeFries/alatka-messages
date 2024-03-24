package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.math.BigInteger;

/**
 * {@link BigInteger}类型报文域解析器
 * TODO
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BigIntegerFieldBuilder extends NumberFieldBuilder<BigInteger> {


    @Override
    protected byte[] fromObjectToAscii(BigInteger value, FieldDefinition fieldDefinition) {
        return new byte[0];
    }

    @Override
    protected byte[] fromObjectToBinary(BigInteger value, FieldDefinition fieldDefinition) {
        return new byte[0];
    }

    @Override
    protected byte[] fromObjectToBcd(BigInteger value, FieldDefinition fieldDefinition) {
        return new byte[0];
    }

    @Override
    protected BigInteger toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return null;
    }

    @Override
    protected BigInteger toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        return null;
    }

    @Override
    protected BigInteger toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return null;
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 3;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == BigInteger.class;
    }
}
