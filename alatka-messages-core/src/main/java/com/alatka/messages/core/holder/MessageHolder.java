package com.alatka.messages.core.holder;

import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.field.FieldBuilderFactory;
import com.alatka.messages.core.support.CustomJsonSerializer;
import com.alatka.messages.core.util.ClassUtil;
import com.alatka.messages.core.util.JsonUtil;
import com.alatka.messages.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 消息报文容器
 *
 * @author ybliu
 */
@SuppressWarnings("unchecked")
@JsonSerialize(using = CustomJsonSerializer.class)
public class MessageHolder {

    private final Map<FieldDefinition, Object> valueMap = new LinkedHashMap<>();

    private MessageDefinition messageDefinition;

    private MessageHolder() {
    }

    public static MessageHolder newInstance(String identity) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(identity);
        if (messageDefinition == null) {
            throw new IllegalArgumentException("identity [" + identity + "] not exist");
        }
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> messageHolder.valueMap.put(fieldDefinition, null));
        return messageHolder;
    }

    public MessageHolder copyOf(String identity) {
        MessageHolder copy = newInstance(identity);
        // TODO validate
        Map<FieldDefinition, Object> copyMap = this.valueMap.entrySet().stream()
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);
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
        if (value != null && value.getClass() != definition.getClassType()) {
            throw new IllegalArgumentException(definition + "类型不匹配，" +
                    "value=" + value + " <" + value.getClass().getName() + ">，配置类型: " + definition.getClassType().getName());
        }
        this.valueMap.put(definition, value);
    }

    public <T> T getByName(String name) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, name);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    public <T> T getByDomainNo(Integer domainNo) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, domainNo);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    public static MessageHolder fromPojo(Object object) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(object.getClass());
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> {
                    Object value = ClassUtil.getFieldValue(object, fieldDefinition.getName());
                    Object result;
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
                    } else if (value instanceof List) {
                        result = ((List<Object>) value).stream().map(MessageHolder::fromPojo).collect(Collectors.toList());
                    } else {
                        result = value;
                    }
                    messageHolder.valueMap.put(fieldDefinition, result);
                });
        return messageHolder;
    }

    public static MessageHolder fromMap(String identity, Map<String, Object> params, boolean byName) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(identity);
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageDefinition)
                .forEach(fieldDefinition -> {
                    Object result;
                    Object value = params.get(byName ? fieldDefinition.getName() : String.valueOf(fieldDefinition.getDomainNo()));
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
                    } else if (value instanceof List) {
                        Map<String, MessageDefinition> mapping = fieldDefinition.getMessageDefinitionMap();
                        result = ((List<Object>) value).stream()
                                .map(item -> fromMap(mapping.get(FieldDefinition.SUBFIELD_KEY_DEFAULT).identity(), (Map<String, Object>) item, byName))
                                .collect(Collectors.toList());
                    } else if (value != null && fieldDefinition.getClassType() != value.getClass()) {
                        result = FieldBuilderFactory.getInstance(messageDefinition, fieldDefinition)
                                .normalizeType(value, fieldDefinition);
                    } else {
                        result = value;
                    }
                    messageHolder.valueMap.put(fieldDefinition, result);
                });
        return messageHolder;
    }

    public Map<String, Object> toMapByName() {
        return this.toMapByName(false);
    }

    /**
     * DomainName 做 Key 转换为 Map<String, Object>
     *
     * @param formatted true 格式化 Map.Entry#getValue(); false 保持原样
     * @return 转换后的 Map<String, Object>
     */
    public Map<String, Object> toMapByName(boolean formatted) {
        return this.valueMap.entrySet().stream()
                .collect(LinkedHashMap::new,
                        (map, entry) -> {
                            String key = entry.getKey().getName();
                            Object val = this.ofValue(entry.getValue(), this.messageDefinition, entry.getKey(), KeyType.byName, formatted);
                            map.put(key, val);
                        },
                        LinkedHashMap::putAll);
    }

    public Map<String, Object> toMapByDomainNo() {
        return this.toMapByDomainNo(false);
    }

    /**
     * DomainNo 做 Key 转换为 Map<String, Object>
     *
     * @param formatted true 格式化 Map.Entry#getValue(); false 保持原样
     * @return 转换后的 Map<String, Object>
     */
    public Map<String, Object> toMapByDomainNo(boolean formatted) {
        return this.valueMap.entrySet().stream()
                .collect(LinkedHashMap::new,
                        (map, entry) -> {
                            String key = entry.getKey().getDomainNo().toString();
                            Object val = this.ofValue(entry.getValue(), this.messageDefinition, entry.getKey(), KeyType.byDomainNo, formatted);
                            map.put(key, val);
                        },
                        LinkedHashMap::putAll);
    }

    private Object ofValue(Object value, MessageDefinition messageDefinition, FieldDefinition fieldDefinition, KeyType keyType, boolean formatted) {
        if (value == null) {
            return null;
        }
        if (value instanceof MessageHolder) {
            MessageHolder messageHolder = (MessageHolder) value;
            return messageHolder.valueMap.entrySet().stream()
                    .collect(LinkedHashMap::new,
                            (map, entry) -> {
                                String key = keyType.generateKey(entry.getKey());
                                Object val = this.ofValue(entry.getValue(), messageHolder.messageDefinition, entry.getKey(), keyType, formatted);
                                map.put(key, val);
                            },
                            LinkedHashMap::putAll);
        }
        if (value instanceof List) {
            Map<String, MessageDefinition> mapping = fieldDefinition.getMessageDefinitionMap();
            return ((List<Object>) value).stream()
                    .map(item -> this.ofValue(item, mapping.get(FieldDefinition.SUBFIELD_KEY_DEFAULT), fieldDefinition, keyType, formatted))
                    .collect(Collectors.toList());
        }
        if (value instanceof UsageSubdomain) {
            Map<String, MessageDefinition> mapping = fieldDefinition.getMessageDefinitionMap();
            return ((UsageSubdomain<Object>) value).getHolder().entrySet()
                    .stream()
                    .collect(LinkedHashMap::new,
                            (map, entry) -> {
                                String key = entry.getKey();
                                MessageDefinition definition;
                                if (mapping.containsKey(key)) {
                                    definition = mapping.get(key);
                                } else if (entry.getValue() instanceof byte[]) {
                                    definition = mapping.values().stream()
                                            .findFirst().orElseThrow(() -> new IllegalArgumentException(fieldDefinition + "#getMessageDefinitionMap() is empty"));
                                } else {
                                    throw new IllegalArgumentException(fieldDefinition + "messageDefinitionMap (" + mapping.keySet() + ") do not contain [" + key + "]");
                                }
                                Object val = this.ofValue(entry.getValue(), definition, fieldDefinition, keyType, formatted);
                                map.put(key, val);
                            },
                            LinkedHashMap::putAll);
        }
        return formatted ? FieldBuilderFactory.getInstance(messageDefinition, fieldDefinition).denormalizeType(value, fieldDefinition) : value;
    }

    private enum KeyType {
        byName(FieldDefinition::getName),
        byDomainNo(fieldDefinition -> fieldDefinition.getDomainNo().toString()),
        raw(Object::toString);

        private final Function<FieldDefinition, String> function;

        KeyType(Function<FieldDefinition, String> function) {
            this.function = function;
        }

        public String generateKey(FieldDefinition definition) {
            return this.function.apply(definition);
        }
    }

    public MessageDefinition getMessageDefinition() {
        return this.messageDefinition;
    }

    @Override
    public String toString() {
        Map<Object, Object> result = this.valueMap.entrySet().stream()
                .collect(LinkedHashMap::new,
                        (map, entry) -> {
                            String key = entry.getKey().toString();
                            Object val = this.ofValue(entry.getValue(), this.messageDefinition, entry.getKey(), KeyType.raw, true);
                            map.put(key, val);
                        },
                        LinkedHashMap::putAll);
        return JsonUtil.format(result);
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
