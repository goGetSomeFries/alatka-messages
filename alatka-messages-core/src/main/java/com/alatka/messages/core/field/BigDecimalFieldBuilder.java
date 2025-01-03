package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.math.BigDecimal;

/**
 * {@link BigDecimal}类型报文域解析器
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
        return BytesUtil.binaryToBytes(str);
    }

    @Override
    protected byte[] fromObjectToBcd(BigDecimal value, FieldDefinition fieldDefinition) {
        return BytesUtil.toBCD(value.toString());
    }

    @Override
    protected byte[] fromObjectToEbcdic(BigDecimal value, FieldDefinition fieldDefinition) {
        return BytesUtil.toEBCDIC(value.toString());
    }

    @Override
    protected BigDecimal toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigDecimal(new String(bytes));
    }

    @Override
    protected BigDecimal toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        String binaryStr = BytesUtil.bytesToBinary(bytes);
        return new BigDecimal(Long.parseLong(binaryStr, 2));
    }

    @Override
    protected BigDecimal toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        return new BigDecimal(BytesUtil.fromBCD(bytes));
    }

    @Override
    protected BigDecimal toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        String str = BytesUtil.fromEBCDIC(bytes);
        return new BigDecimal(str);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 3;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == BigDecimal.class;
    }
}
