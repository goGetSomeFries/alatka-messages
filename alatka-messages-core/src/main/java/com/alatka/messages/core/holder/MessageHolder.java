package com.alatka.messages.core.holder;

import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.support.CustomJsonSerializer;
import com.alatka.messages.core.util.ClassUtil;
import com.alatka.messages.core.util.JsonUtil;
import com.alatka.messages.core.util.ObjectUtil;
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
    public static MessageHolder fromPojo(Object object) {
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
                        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
                        Map<String, Object> map = ((UsageSubdomain<Object>) value).getHolder().entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        entry -> entry.getValue() instanceof byte[] ? entry.getValue() : fromPojo(entry.getValue())));
                        usageSubdomain.putAll(map);
                        result = usageSubdomain;
                    } else if (value.getClass().isAnnotationPresent(MessageMeta.class)) {
                        result = fromPojo(value);
                    } else {
                        result = value;
                    }
                    messageHolder.valueMap.put(fieldDefinition, result);
                });
        return messageHolder;
    }

    public static MessageHolder fromMap(String key, Map<String, Object> params, boolean byName) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(key);
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> {
                    Object result = null;
                    Object value = params.get(byName ? fieldDefinition.getName() : fieldDefinition.getDomainNo());
                    if (fieldDefinition.getExistSubdomain() && value instanceof Map) {
                        if (fieldDefinition.getStatus() == FieldDefinition.Status.RAW) {
                            throw new IllegalArgumentException("类型错误：" + messageDefinition + " " + fieldDefinition);
                        }

                        Map<String, MessageDefinition> mapping = fieldDefinition.getMessageDefinitionMap();
                        if (mapping.containsKey(FieldDefinition.SUBFIELD_KEY_DEFAULT)) {
                            result = fromMap(mapping.get(FieldDefinition.SUBFIELD_KEY_DEFAULT).identity(), (Map<String, Object>) value, byName);
                        } else {
                            UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
                            Map<String, Object> map = ((Map<String, Object>) value).entrySet().stream()
                                    .collect(Collectors.toMap(Map.Entry::getKey,
                                            entry -> entry.getValue() instanceof byte[] ? entry.getValue() :
                                                    fromMap(mapping.get(entry.getKey()).identity(), (Map<String, Object>) entry.getValue(), byName)));
                            usageSubdomain.putAll(map);
                            result = usageSubdomain;
                        }
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

    public Map<String, Object> toMapByName() {
        return this.valueMap.entrySet().stream()
                .collect(HashMap::new,
                        (map, item) -> map.put(item.getKey().getName(), this.ofValue(item.getValue(), true)),
                        HashMap::putAll);
    }

    public Map<String, Object> toMapByDomainNo() {
        return this.valueMap.entrySet().stream()
                .collect(HashMap::new,
                        (map, item) -> map.put(item.getKey().getDomainNo().toString(), this.ofValue(item.getValue(), false)),
                        HashMap::putAll);
    }

    private Object ofValue(Object value, boolean byName) {
        if (value instanceof MessageHolder) {
            return ((MessageHolder) value).valueMap.entrySet().stream()
                    .collect(HashMap::new,
                            (map, item) -> map.put(byName ? item.getKey().getName() : item.getKey().getDomainNo().toString(), this.ofValue(item.getValue(), byName)),
                            HashMap::putAll);
        }
        if (value instanceof UsageSubdomain) {
            return ((UsageSubdomain<Object>) value).getHolder().entrySet()
                    .stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> this.ofValue(entry.getValue(), byName)));
        }
        return value;
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
