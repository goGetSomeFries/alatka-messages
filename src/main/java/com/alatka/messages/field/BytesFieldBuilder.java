package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * byte[]类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BytesFieldBuilder extends AbstractFieldBuilder<byte[]> {

    @Override
    protected byte[] fromObjectToNone(byte[] value, FieldDefinition fieldDefinition) {
        return value;
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
        return definition.getClazz() == byte[].class;
    }
}
