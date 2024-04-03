package com.alatka.messages.holder;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.util.BytesUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
                && Objects.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueMap, messageDefinition);
    }
}
