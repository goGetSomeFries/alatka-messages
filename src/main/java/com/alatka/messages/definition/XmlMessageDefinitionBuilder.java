package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.support.Constant;
import com.alatka.messages.util.ClassUtil;
import com.alatka.messages.util.FileUtil;
import com.alatka.messages.util.JsonUtil;
import com.alatka.messages.util.XmlUtil;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * xml文件报文定义解析器
 *
 * @author ybliu
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class XmlMessageDefinitionBuilder extends AbstractMessageDefinitionBuilder<Path> {

    private final String classpath;

    public XmlMessageDefinitionBuilder(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected <S extends FieldDefinition> List<S> buildFieldDefinitions(MessageDefinition definition, Path source) {
        Map<String, Object> xml = XmlUtil.getMap(source.toFile(), Object.class);
        Map<String, Object> message = this.getValueWithMap(xml, "message");
        MessageDefinition.Kind kind = definition.getKind();

        Map<String, Object> result;
        if (kind == MessageDefinition.Kind.subPayload) {
            Object value = this.getValueWithMap(message, kind.name());
            result = value instanceof Map ? (Map<String, Object>) value
                    : ((List<Map<String, Object>>) value).stream()
                    .filter(map -> Objects.equals(map.get("domain"), definition.getDomain())
                            && Objects.equals(map.get("usage"), definition.getUsage()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("未匹配子域"));
        } else {
            result = this.getValueWithMap(message, kind.name());
        }
        List<Map<String, Object>> list = this.getValueWithMap(result, "field");

        AtomicInteger counter = new AtomicInteger(0);
        return list.stream()
                .map(map -> JsonUtil.mapToObject(map, fieldDefinitionClass()))
                .map(entity -> (S) entity)
                .peek(fieldDefinition -> this.buildFieldDefinition(definition, fieldDefinition, counter))
                .peek(fieldDefinition -> this.postBuildFieldDefinition(definition, fieldDefinition))
                .collect(Collectors.toList());
    }

    private void buildFieldDefinition(MessageDefinition definition, FieldDefinition fieldDefinition, AtomicInteger counter) {
        fieldDefinition.setIndex(counter.getAndIncrement());
        if (fieldDefinition.getFixed() == null) {
            fieldDefinition.setFixed(Boolean.TRUE);
        }
        if (fieldDefinition.getStatus() == null) {
            fieldDefinition.setStatus(FieldDefinition.Status.OPEN);
        }
        if (fieldDefinition.getParseType() == null) {
            FieldDefinition.ParseType parseType =
                    fieldDefinition.getExistSubdomain() || fieldDefinition.getClazz() == byte[].class ?
                            FieldDefinition.ParseType.NONE : FieldDefinition.ParseType.ASCII;
            fieldDefinition.setParseType(parseType);
        }
        if (fieldDefinition.getExistSubdomain()) {
            if (fieldDefinition.getClazz() == null) {
                fieldDefinition.setOriginClass(MessageHolder.class);
            }
            List<MessageDefinition> list = MessageDefinitionContext.getInstance()
                    .childrenMessageDefinitions(definition, fieldDefinition);
            Map<String, MessageDefinition> messageDefinitionMap =
                    list.stream().collect(Collectors.toMap(d ->
                            d.getUsage().isEmpty() ? FieldDefinition.SUBFIELD_KEY_DEFAULT : d.getUsage(), Function.identity()));
            fieldDefinition.setMessageDefinitionMap(messageDefinitionMap);
        }
    }

    @Override
    protected List<MessageDefinition> buildMessageDefinitions(Path source) {
        Map<String, Object> xml = XmlUtil.getMap(source.toFile(), Object.class);
        Map<String, Object> message = this.getValueWithMap(xml, "message");

        AtomicReference<MessageDefinition> header = new AtomicReference<>();

        return Arrays.stream(MessageDefinition.Kind.values())
                .filter(kind -> message.containsKey(kind.name()))
                .flatMap(kind -> {
                    if (kind == MessageDefinition.Kind.subPayload) {
                        Object value = this.getValueWithMap(message, kind.name());
                        List<Map<String, Object>> list =
                                (List<Map<String, Object>>) (value instanceof Map ? Collections.singletonList(value) : value);
                        return list.stream().map(subPayload -> buildMessageDefinition(kind, subPayload, message));
                    }
                    return Stream.of(buildMessageDefinition(kind, null, message));
                }).peek(definition -> {
                    if (definition.getKind() == MessageDefinition.Kind.header) {
                        header.set(definition);
                    }
                    if (definition.getKind() == MessageDefinition.Kind.payload ||
                            definition.getKind() == MessageDefinition.Kind.request ||
                            definition.getKind() == MessageDefinition.Kind.response) {
                        definition.setHeader(header.get());
                    }
                }).collect(Collectors.toList());
    }

    private MessageDefinition buildMessageDefinition(MessageDefinition.Kind kind,
                                                     Map<String, Object> subPayload,
                                                     Map<String, Object> message) {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(MessageDefinition.Type.valueOf(message.get("type").toString()));
        definition.setGroup(message.get("group").toString());
        definition.setCode(message.get("code").toString());
        definition.setKind(kind);
        definition.setCharset(message.get("charset") == null ? Constant.DEFAULT_CHARSET : message.get("charset").toString());
        definition.setRemark(message.get("remark").toString());
        definition.setHolder(message.get("clazz") == null ? MessageHolder.class : ClassUtil.forName(message.get("clazz").toString()));

        if (subPayload != null) {
            definition.setDomain(subPayload.get("domain").toString());
            definition.setUsage(subPayload.get("usage") == null ? "" : subPayload.get("usage").toString());
            definition.setDomainType(subPayload.get("domainType") == null ? MessageDefinition.DomainType.NONE :
                    MessageDefinition.DomainType.valueOf(subPayload.get("domainType").toString()));
        } else {
            definition.setDomain("");
            definition.setUsage("");
            definition.setDomainType(MessageDefinition.DomainType.NONE);
        }

        return definition;
    }

    private <T> T getValueWithMap(Map<String, Object> map, String key) {
        return (T) map.get(key);
    }

    @Override
    protected List<Path> getSources() {
        return FileUtil.getClasspathFiles(this.classpath, "*." + this.type() + ".xml");
    }

    /**
     * {@link FieldDefinition}后处理器
     *
     * @param definition      {@link MessageDefinition}
     * @param fieldDefinition {@link FieldDefinition}
     */
    protected abstract void postBuildFieldDefinition(MessageDefinition definition, FieldDefinition fieldDefinition);

    /**
     * get {@link FieldDefinition} type
     *
     * @param <S> {@link FieldDefinition}
     * @return {@link FieldDefinition} type
     */
    protected abstract <S extends FieldDefinition> Class<S> fieldDefinitionClass();

}
