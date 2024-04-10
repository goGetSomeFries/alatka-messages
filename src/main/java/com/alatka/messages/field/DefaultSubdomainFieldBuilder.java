package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.message.IsoUnfixedSubDomainMessageBuilder;
import com.alatka.messages.message.MessageBuilder;

import java.util.Map;

/**
 * 子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class DefaultSubdomainFieldBuilder<T> extends SubdomainFieldBuilder<T> {

    @Override
    protected byte[] pack(T value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return fieldDefinition.getFixed() ?
                MessageBuilder.init(definition).pack(value) :
                new IsoUnfixedSubDomainMessageBuilder(definition).pack(value);
    }

    @Override
    protected T unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return fieldDefinition.getFixed() ?
                MessageBuilder.init(definition).unpack(bytes) :
                new IsoUnfixedSubDomainMessageBuilder(definition).unpack(bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.DEFAULT;
    }
}
