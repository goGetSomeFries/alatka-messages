package com.alatka.messages.holder;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.util.BytesUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 消息报文容器
 *
 * @author ybliu
 */
public class MessageHolder {

    private final Map<FieldDefinition, Object> valueMap = new HashMap<>();

    private MessageDefinition messageDefinition;

    private MessageHolder() {
    }

    public static MessageHolder newInstance(String key) {
        MessageHolder messageHolder = new MessageHolder();
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition messageDefinition = context.messageDefinition(key);
        if (messageDefinition == null) {
            throw new IllegalArgumentException("key [" + key + "] not exist");
        }
        messageHolder.messageDefinition = messageDefinition;
        context.fieldDefinitions(messageHolder.messageDefinition)
                .forEach(definition -> messageHolder.valueMap.put(definition, null));
        return messageHolder;
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

    public <T> T getByName(String name) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, name);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    public <T> T getByDomainNo(Integer domainNo) {
        FieldDefinition definition = MessageDefinitionContext.getInstance().fieldDefinition(this.messageDefinition, domainNo);
        return definition == null ? null : (T) this.valueMap.get(definition);
    }

    public MessageDefinition getMessageDefinition() {
        return this.messageDefinition;
    }

    @Override
    public String toString() {
        return messageDefinition + valueMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(FieldDefinition::getIndex)))
                .map(entry -> entry.getValue() != null && entry.getValue() instanceof byte[] ?
                        entry.getKey() + "=" + BytesUtil.bytesToHex((byte[]) entry.getValue()) : entry.toString())
                .collect(Collectors.joining("\n\t", "\n\t", ""));
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
                && this.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueMap, messageDefinition);
    }

    private boolean equals(Map<FieldDefinition, Object> map1, Map<FieldDefinition, Object> map2) {
        if (map1 == map2) {
            return true;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        try {
            Iterator<Map.Entry<FieldDefinition, Object>> i = map1.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<FieldDefinition, Object> e = i.next();
                FieldDefinition key = e.getKey();
                Object value = e.getValue();
                if (value == null) {
                    if (!(map2.get(key) == null && map2.containsKey(key))) {
                        return false;
                    }
                } else {
                    if (value instanceof byte[]) {
                        if (!Arrays.equals((byte[]) value, (byte[]) map2.get(key))) {
                            return false;
                        }
                    } else {
                        if (!value.equals(map2.get(key))) {
                            return false;
                        }
                    }
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }
}
