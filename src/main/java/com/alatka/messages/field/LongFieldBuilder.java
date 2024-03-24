package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

/**
 * Long类型报文域解析器
 *
 * @author ybliu
 * @see NumberFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class LongFieldBuilder extends NumberFieldBuilder<Long> {

    @Override
    protected Long toObjectWithAscii(byte[] bytes, FieldDefinition definition) {
        return Long.parseLong(new String(bytes));
    }

    @Override
    protected Long toObjectWithBinary(byte[] bytes, FieldDefinition definition) {
        String binaryStr = BytesUtil.toString(bytes);
        return Long.parseLong(binaryStr, 2);
    }

    @Override
    protected Long toObjectWithBcd(byte[] bytes, FieldDefinition definition) {
        return Long.parseLong(BytesUtil.fromBCD(bytes));
    }

    @Override
    protected byte[] fromObjectToAscii(Long value, FieldDefinition definition) {
        return String.valueOf(value).getBytes();
    }

    @Override
    protected byte[] fromObjectToBinary(Long value, FieldDefinition definition) {
        String str = Long.toBinaryString(value);
        return BytesUtil.toBytes(str);
    }

    @Override
    protected byte[] fromObjectToBcd(Long value, FieldDefinition definition) {
        byte[] bytes = String.valueOf(value).getBytes();
        return BytesUtil.toBCD(bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 1;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == Long.class;
    }
}
