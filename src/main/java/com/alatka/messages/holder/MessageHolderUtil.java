package com.alatka.messages.holder;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.util.ClassUtil;

public class MessageHolderUtil {

    public static <T> T getByName(Object instance, String name) {
        if (instance instanceof MessageHolder) {
            return ((MessageHolder) instance).getByName(name);
        }
        return (T) ClassUtil.getFieldValue(instance, name);
    }

    public static <T> T getByDomainNo(MessageDefinition messageDefinition, Object instance, Integer domainNo) {
        if (instance instanceof MessageHolder) {
            return ((MessageHolder) instance).getByDomainNo(domainNo);
        }
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(messageDefinition, domainNo);
        if (definition == null) {
            throw new IllegalArgumentException(messageDefinition + ", domainNo: " + domainNo);
        }
        return (T) ClassUtil.getFieldValue(instance, definition.getName());
    }
}
