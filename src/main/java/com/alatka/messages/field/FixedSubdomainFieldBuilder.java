package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.message.DefaultMessageBuilder;
import com.alatka.messages.message.UnfixedSubdomainMessageBuilder;

import java.util.Map;

/**
 * FIXED子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class FixedSubdomainFieldBuilder<T> extends SubdomainFieldBuilder<T> {

    @Override
    protected byte[] pack(T value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return fieldDefinition.getFixed() ?
                new DefaultMessageBuilder(definition).pack(value) :
                new UnfixedSubdomainMessageBuilder(definition).pack(value);
    }

    @Override
    protected T unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return fieldDefinition.getFixed() ?
                new DefaultMessageBuilder(definition).unpack(bytes) :
                new UnfixedSubdomainMessageBuilder(definition).unpack(bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.FIXED;
    }
}
