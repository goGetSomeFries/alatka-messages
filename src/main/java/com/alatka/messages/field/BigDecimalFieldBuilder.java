package com.alatka.messages.field;

import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.math.BigDecimal;

/**
 * BigDecimal类型报文域解析器
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BigDecimalFieldBuilder extends NumberFieldBuilder<BigDecimal> {


    @Override
    protected byte[] fromObjectToAscii(BigDecimal value, FieldDefinition fieldDefinition) {
        return value.toString().getBytes();
    }

    @Override
    protected byte[] fromObjectToBinary(BigDecimal value, FieldDefinition fieldDefinition) {
        String str = Long.toBinaryString(value.longValue());
        return BytesUtil.toBytes(str);
    }

    @Override
    protected byte[] fromObjectToBcd(BigDecimal value, FieldDefinition fieldDefinition) {
        byte[] bytes = value.toString().getBytes();
        return BytesUtil.toBCD(bytes);
    }

    @Override
    protected BigDecimal toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigDecimal(new String(bytes));
    }

    @Override
    protected BigDecimal toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        String binaryStr = BytesUtil.toString(bytes);
        return new BigDecimal(Long.parseLong(binaryStr, 2));
    }

    @Override
    protected BigDecimal toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigDecimal(BytesUtil.fromBCD(bytes));
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 3;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == BigDecimal.class;
    }
}
