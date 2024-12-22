package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;

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
        if (definition.getFixed()) {
            return BytesUtil.toBCD(value);
        }
        byte[] lenBytes = BytesUtil.intToBytes(value.length());
        byte[] valueBytes = BytesUtil.toBCD(value);
        return BytesUtil.concat(new byte[definition.getLength() - lenBytes.length], lenBytes, valueBytes);
    }

    @Override
    protected byte[] fromObjectToEbcdic(String value, FieldDefinition definition) {
        return BytesUtil.toEBCDIC(value);
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
        if (definition.getFixed()) {
            String result = BytesUtil.fromBCD(bytes);
            return this.postProcess(result);
        }

        Integer lenLength = definition.getLength();
        int length = BytesUtil.bytesToInt(Arrays.copyOfRange(bytes, 0, lenLength));
        String value = BytesUtil.fromBCD(Arrays.copyOfRange(bytes, lenLength, bytes.length));
        String result = length == value.length() ? value : value.substring(value.length() - length);
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
        return definition.getClassType() == String.class;
    }
}
