package com.alatka.messages.context;

import com.alatka.messages.annotation.MessageMeta;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 报文/报文域定义Context
 *
 * @author ybliu
 */
public class MessageDefinitionContext {

    /**
     * {@link MessageDefinition} context
     */
    private final Map<String, MessageDefinition> messageDefinitionMap = new ConcurrentHashMap<>();

    /**
     * {@link FieldDefinition} context
     */
    private final Map<MessageDefinition, List<FieldDefinition>> fieldDefinitionsMap = new ConcurrentHashMap<>();

    private MessageDefinitionContext() {
    }

    public void messageDefinition(String key, MessageDefinition definition) {
        this.messageDefinitionMap.put(key, definition);
    }

    public MessageDefinition messageDefinition(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(MessageMeta.class)) {
            throw new RuntimeException("class: '" + clazz + "' is not annotation by 'MessageMeta'");
        }
        MessageMeta annotation = clazz.getAnnotation(MessageMeta.class);
        MessageDefinition definition = new MessageDefinition();
        definition.setType(annotation.type());
        definition.setGroup(annotation.group());
        definition.setCode(annotation.code());
        definition.setKind(annotation.kind());
        definition.setDomain(annotation.domain());
        definition.setUsage(annotation.usage());
        return messageDefinition(definition);
    }

    public MessageDefinition messageDefinition(MessageDefinition condition) {
        return this.messageDefinition(condition.identity());
    }

    public MessageDefinition messageDefinition(String key) {
        return this.messageDefinitionMap.get(key);
    }

    public List<MessageDefinition> childrenMessageDefinitions(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        MessageDefinition condition = messageDefinition.copy();
        condition.setKind(MessageDefinition.Kind.subPayload);
        String domain = messageDefinition.getKind() == MessageDefinition.Kind.subPayload ?
                messageDefinition.getDomain()
                        .concat(messageDefinition.getUsage().isEmpty() ? "" : "$".concat(messageDefinition.getUsage()))
                        .concat("_F").concat(fieldDefinition.getDomainNo().toString()) :
                "F".concat(fieldDefinition.getDomainNo().toString());
        condition.setDomain(domain);
        condition.setUsage("");

        return this.messageDefinitionMap.values().stream()
                .filter(md -> md.getType() == condition.getType()
                        && md.getGroup().equals(condition.getGroup())
                        && md.getCode().equals(condition.getCode())
                        && md.getKind() == condition.getKind()
                        && md.getDomain().equals(condition.getDomain()))
                .collect(Collectors.toList());
    }

    public void fieldDefinitions(MessageDefinition definition, List<FieldDefinition> list) {
        this.fieldDefinitionsMap.put(definition, list);
    }

    public List<FieldDefinition> fieldDefinitions(MessageDefinition definition) {
        return this.fieldDefinitionsMap.get(definition);
    }

    public FieldDefinition fieldDefinition(MessageDefinition messageDefinition, Integer domainNo) {
        return this.fieldDefinitions(messageDefinition).stream()
                .filter(definition -> domainNo.compareTo(definition.getDomainNo()) == 0)
                .findFirst().orElse(null);
    }

    public FieldDefinition fieldDefinition(MessageDefinition messageDefinition, String fieldName) {
        return this.fieldDefinitions(messageDefinition).stream()
                .filter(definition -> fieldName.equals(definition.getName()))
                .findFirst().orElse(null);
    }

    public static MessageDefinitionContext getInstance() {
        return Inner.INSTANCE;
    }

    private static class Inner {
        private static final MessageDefinitionContext INSTANCE = new MessageDefinitionContext();
    }
}
