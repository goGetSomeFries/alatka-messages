package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.Map;

/**
 * 子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public abstract class SubdomainFieldBuilder<T> extends AbstractFieldBuilder<T> {

    protected abstract byte[] pack(T value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap);

    protected abstract T unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap);

    @Override
    protected byte[] fromObjectToNone(T value, FieldDefinition fieldDefinition) {
        return this.pack(value, fieldDefinition, fieldDefinition.getMessageDefinitionMap());
    }

    @Override
    protected T toObjectWithNone(byte[] bytes, FieldDefinition fieldDefinition) {
        return this.unpack(bytes, fieldDefinition, fieldDefinition.getMessageDefinitionMap());
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 100;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getExistSubdomain();
    }

    protected void validate(FieldDefinition fieldDefinition, String usageId) {
        Map<String, MessageDefinition> map = fieldDefinition.getMessageDefinitionMap();
        if (map.isEmpty()) {
            throw new RuntimeException(fieldDefinition + "未配置子域");
        }
        if (!map.containsKey(usageId)) {
            throw new RuntimeException(fieldDefinition + "未配置子域usage: " + usageId);
        }
    }
}
