package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.message.DefaultMessageBuilder;
import com.alatka.messages.core.message.UnfixedSubdomainMessageBuilder;

import java.util.Map;

/**
 * FIXED子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class FixedSubdomainFieldBuilder extends SubdomainFieldBuilder<Object> {

    @Override
    protected byte[] pack(Object value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return fieldDefinition.getFixed() ?
                new DefaultMessageBuilder(definition).pack(value) :
                new UnfixedSubdomainMessageBuilder(definition).pack(value);
    }

    @Override
    protected Object unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
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
