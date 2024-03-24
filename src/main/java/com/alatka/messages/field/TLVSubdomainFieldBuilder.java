package com.alatka.messages.field;

import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.Map;

/**
 * TLV子域类型报文域解析器<br>
 * 参考F55域
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class TLVSubdomainFieldBuilder extends SubdomainFieldBuilder<Object> {

    @Override
    protected byte[] pack(Object value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return MessageBuilder.init(definition).pack(value);
    }

    @Override
    protected Object unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);

        super.validate(fieldDefinition, FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return MessageBuilder.init(definition).unpack(bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 4;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.TLV;
    }
}
