package com.alatka.messages.holder;

import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.support.CustomJsonSerializer;
import com.alatka.messages.util.ClassUtil;
import com.alatka.messages.util.JsonUtil;
import com.alatka.messages.util.ObjectUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 消息报文容器
 *
 * @author ybliu
 */
@JsonSerialize(using = CustomJsonSerializer.class)
public class MessageHolder {

    private final Map<FieldDefinition, Object> valueMap = new HashMap<>();

    private MessageDefinition messageDefinition;

    private MessageHolder() {
    }

    @SuppressWarnings("unchecked")
    public static <T> MessageHolder fromPojo(T object) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(object.getClass());
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> {
                    Object value = ClassUtil.getFieldValue(object, fieldDefinition.getName());
                    Object result = null;
                    if (value == null) {
                        result = null;
                    } else if (value.getClass() == UsageSubdomain.class) {
                        result = ((UsageSubdomain<Object>) value).getHolder().entrySet().stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        entry -> entry.getValue() instanceof byte[] ? entry.getValue() : fromPojo(entry.getValue())));
                    } else if (value.getClass().isAnnotationPresent(MessageMeta.class)) {
                        result = fromPojo(value);
                    } else {
                        result = value;
                    }
                    messageHolder.valueMap.put(fieldDefinition, result);
                });
        return messageHolder;
    }

    public static MessageHolder newInstance(String key) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(key);
        if (messageDefinition == null) {
            throw new IllegalArgumentException("key [" + key + "] not exist");
        }
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> messageHolder.valueMap.put(fieldDefinition, null));
        return messageHolder;
    }

    public MessageHolder copyOf(String key) {
        MessageHolder copy = newInstance(key);
        // TODO validate
        Map<FieldDefinition, Object> copyMap = this.valueMap.entrySet().stream()
                .collect(HashMap::new, (map, item) -> map.put(item.getKey(), item.getValue()), HashMap::putAll);
        copy.valueMap.putAll(copyMap);
        return copy;
    }

    public MessageHolder put(String name, Object value) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, name);
        this.put(definition, value);
        return this;
    }

    public MessageHolder put(Integer domainNo, Object value) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, domainNo);
        this.put(definition, value);
        return this;
    }

    public void put(FieldDefinition definition, Object value) {
        this.valueMap.put(definition, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getByName(String name) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, name);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    @SuppressWarnings("unchecked")
    public <T> T getByDomainNo(Integer domainNo) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, domainNo);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    public MessageDefinition getMessageDefinition() {
        return this.messageDefinition;
    }

    @Override
    public String toString() {
        return JsonUtil.format(valueMap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageHolder)) {
            return false;
        }
        MessageHolder that = (MessageHolder) o;
        return Objects.equals(messageDefinition, that.messageDefinition)
                && ObjectUtil.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueMap, messageDefinition);
    }

}
