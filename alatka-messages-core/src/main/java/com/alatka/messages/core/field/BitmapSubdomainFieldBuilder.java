package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.message.BitmapSubdomainMessageBuilder;

import java.util.Map;

/**
 * Bitmap子域类型报文域解析器<br>
 * 参考visa 62域
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BitmapSubdomainFieldBuilder extends SubdomainFieldBuilder<Object> {

    @Override
    protected byte[] pack(Object value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return new BitmapSubdomainMessageBuilder(definition).pack(value);
    }

    @Override
    protected Object unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return new BitmapSubdomainMessageBuilder(definition).unpack(bytes);
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.BITMAP;
    }
}
