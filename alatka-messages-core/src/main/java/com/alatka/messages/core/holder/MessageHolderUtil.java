package com.alatka.messages.core.holder;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.util.ClassUtil;

/**
 * @author ybliu
 */
@SuppressWarnings("unchecked")
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
