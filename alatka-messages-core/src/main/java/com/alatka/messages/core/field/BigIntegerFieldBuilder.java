package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.math.BigInteger;

/**
 * {@link BigInteger}类型报文域解析器
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BigIntegerFieldBuilder extends NumberFieldBuilder<BigInteger> {


    @Override
    protected byte[] fromObjectToAscii(BigInteger value, FieldDefinition fieldDefinition) {
        return value.toString().getBytes();
    }

    @Override
    protected byte[] fromObjectToBinary(BigInteger value, FieldDefinition fieldDefinition) {
        return value.toByteArray();
    }

    @Override
    protected byte[] fromObjectToBcd(BigInteger value, FieldDefinition fieldDefinition) {
        return BytesUtil.toBCD(value.toString());
    }

    @Override
    protected byte[] fromObjectToEbcdic(BigInteger value, FieldDefinition fieldDefinition) {
        return BytesUtil.toEBCDIC(value.toString());
    }

    @Override
    protected BigInteger toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigInteger(new String(bytes));
    }

    @Override
    protected BigInteger toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigInteger(bytes);
    }

    @Override
    protected BigInteger toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigInteger(BytesUtil.fromBCD(bytes));
    }

    @Override
    protected BigInteger toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        String str = BytesUtil.fromEBCDIC(bytes);
        return new BigInteger(str);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 4;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == BigInteger.class;
    }
}
