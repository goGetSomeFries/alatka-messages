package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * {@link Integer}类型报文域解析器
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class IntegerFieldBuilder extends NumberFieldBuilder<Integer> {

    @Override
    protected Integer toObjectWithAscii(byte[] bytes, FieldDefinition definition) {
        return Integer.parseInt(new String(bytes));
    }

    @Override
    protected Integer toObjectWithBinary(byte[] bytes, FieldDefinition definition) {
        String binaryStr = BytesUtil.bytesToBinary(bytes);
        return Integer.parseInt(binaryStr, 2);
    }

    @Override
    protected Integer toObjectWithBcd(byte[] bytes, FieldDefinition definition) {
        return Integer.parseInt(BytesUtil.fromBCD(bytes));
    }

    @Override
    protected Integer toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        String str = BytesUtil.fromEBCDIC(bytes);
        return Integer.parseInt(str);
    }

    @Override
    protected byte[] fromObjectToAscii(Integer value, FieldDefinition definition) {
        return String.valueOf(value).getBytes();
    }

    @Override
    protected byte[] fromObjectToBinary(Integer value, FieldDefinition definition) {
        String str = Integer.toBinaryString(value);
        return BytesUtil.binaryToBytes(str);
    }

    @Override
    protected byte[] fromObjectToBcd(Integer value, FieldDefinition definition) {
        return BytesUtil.toBCD(String.valueOf(value));
    }

    @Override
    protected byte[] fromObjectToEbcdic(Integer value, FieldDefinition fieldDefinition) {
        return BytesUtil.toEBCDIC(String.valueOf(value));
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 2;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == Integer.class;
    }
}
