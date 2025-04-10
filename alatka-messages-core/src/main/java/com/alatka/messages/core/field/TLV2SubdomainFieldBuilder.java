package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.message.TLV2SubdomainMessageBuilder;

import java.util.Map;

/**
 * TLV2(tag-length-value)子域类型报文域解析器<br>
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class TLV2SubdomainFieldBuilder extends SubdomainFieldBuilder<Object> {

    @Override
    protected byte[] pack(Object value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return new TLV2SubdomainMessageBuilder(definition).pack(value);
    }

    @Override
    protected Object unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return new TLV2SubdomainMessageBuilder(definition).unpack(bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.TLV2;
    }
}
