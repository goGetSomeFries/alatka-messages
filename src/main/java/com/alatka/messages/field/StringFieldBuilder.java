package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

/**
 * {@link String}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class StringFieldBuilder extends AbstractFieldBuilder<String> {

    @Override
    protected byte[] fromObjectToAscii(String value, FieldDefinition fieldDefinition) {
        return value.getBytes(messageDefinition.getCharset());
    }

    @Override
    protected byte[] fromObjectToBinary(String value, FieldDefinition fieldDefinition) {
        return BytesUtil.binaryToBytes(value);
    }

    @Override
    protected byte[] fromObjectToBcd(String value, FieldDefinition definition) {
        return BytesUtil.toBCD(value);
    }

    @Override
    protected byte[] fromObjectToEbcdic(String value, FieldDefinition definition) {
        byte[] bytes = value.getBytes();
        return BytesUtil.toEBCDIC(bytes);
    }

    @Override
    protected String toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        String result = new String(bytes, messageDefinition.getCharset());
        return this.postProcess(result);
    }

    @Override
    protected String toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        String result = BytesUtil.bytesToBinary(bytes);
        return this.postProcess(result);
    }

    @Override
    protected String toObjectWithBcd(byte[] bytes, FieldDefinition definition) {
        String result = BytesUtil.fromBCD(bytes);
        return this.postProcess(result);
    }

    @Override
    protected String toObjectWithEbcdic(byte[] bytes, FieldDefinition definition) {
        String result = BytesUtil.fromEBCDIC(bytes);
        return this.postProcess(result);
    }

    private String postProcess(String result) {
        result = result.trim();
        return result.isEmpty() ? null : result;
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == String.class;
    }
}
