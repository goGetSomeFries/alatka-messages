package com.alatka.messages.definition;

import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.util.JsonUtil;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.util.ClassUtil;
import com.alatka.messages.util.FileUtil;
import com.alatka.messages.util.YamlUtil;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * yaml文件报文定义解析器
 *
 * @author ybliu
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class YamlMessageDefinitionBuilder extends AbstractMessageDefinitionBuilder<Path> {

    /**
     * yaml root element
     */
    private static final String YAML_ROOT_NAME = "alatka.messages";

    /**
     * 子域key Pattern<br>
     * e.g. F61@SM$TLV F59@SM_F3 F60$TLV
     */
    private static final Pattern PATTERN = Pattern.compile("^([@\\w]+?)(@[A-Z]{2})?(\\$\\w+)?$");

    private final String classpath;

    public YamlMessageDefinitionBuilder(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected <S extends FieldDefinition> List<S> buildFieldDefinitions(MessageDefinition definition, Path source) {
        Map<String, Object> yaml = YamlUtil.getMap(source.toFile(), YAML_ROOT_NAME, Object.class);
        Map<String, Object> message = this.getValueWithMap(yaml, "message");
        MessageDefinition.Kind kind = definition.getKind();

        List<Map<String, Object>> list;
        if (kind == MessageDefinition.Kind.subPayload) {
            String domain = definition.getDomain();
            String usage = definition.getUsage().isEmpty() ? "" : "@".concat(definition.getUsage());
            String domainType = definition.getDomainType() == MessageDefinition.DomainType.NONE ?
                    "" : "$".concat(definition.getDomainType().name());

            Map<String, Object> map = this.getValueWithMap(message, kind.name());
            list = this.getValueWithMap(map, domain.concat(usage).concat(domainType));
        } else {
            list = this.getValueWithMap(message, kind.name());
        }

        return list.stream()
                .map(map -> JsonUtil.mapToObject(map, fieldDefinitionClass()))
                .map(entity -> (S) entity)
                .peek(fieldDefinition -> this.buildFieldDefinition(definition, fieldDefinition))
                .peek(fieldDefinition -> this.postBuildFieldDefinition(definition, fieldDefinition))
                .collect(Collectors.toList());
    }

    private void buildFieldDefinition(MessageDefinition definition, FieldDefinition fieldDefinition) {
        if (fieldDefinition.getIndex() == null) {
            fieldDefinition.setIndex(fieldDefinition.getDomainNo());
        }
        if (fieldDefinition.getFixed() == null) {
            fieldDefinition.setFixed(Boolean.TRUE);
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
        Map<String, Object> yaml = YamlUtil.getMap(source.toFile(), YAML_ROOT_NAME, Object.class);

        AtomicReference<MessageDefinition> header = new AtomicReference<>();

        return Arrays.stream(MessageDefinition.Kind.values())
                .filter(kind -> yaml.containsKey("message"))
                .filter(kind -> {
                    Map<String, Object> message = this.getValueWithMap(yaml, "message");
                    return message.containsKey(kind.name());
                }).flatMap(kind -> {
                    if (kind == MessageDefinition.Kind.subPayload) {
                        Map<String, Object> message = this.getValueWithMap(yaml, "message");
                        Map<String, Object> subPayload = this.getValueWithMap(message, kind.name());
                        return subPayload.keySet().stream()
                                .map(identity -> buildMessageDefinition(kind, identity, yaml));
                    }
                    return Stream.of(buildMessageDefinition(kind, null, yaml));
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
                                                     String identity,
                                                     Map<String, Object> yaml) {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(MessageDefinition.Type.valueOf(yaml.get("type").toString()));
        definition.setGroup(yaml.get("group").toString());
        definition.setCode(yaml.get("code").toString());
        definition.setKind(kind);
        definition.setCharset(yaml.get("charset") == null ? "GB18030" : yaml.get("charset").toString());
        definition.setLenParseType(yaml.get("lenParseType") == null ?
                MessageDefinition.LenParseType.ASCII : MessageDefinition.LenParseType.valueOf(yaml.get("lenParseType").toString()));
        definition.setRemark(yaml.get("remark") == null ? "" : yaml.get("remark").toString());

        if (identity != null) {
            Matcher matcher = PATTERN.matcher(identity);
            if (matcher.find()) {
                definition.setDomain(matcher.group(1) == null ? "" : matcher.group(1));
                definition.setUsage(matcher.group(2) == null ? "" : matcher.group(2).substring(1));
                definition.setDomainType(matcher.group(3) == null ?
                        MessageDefinition.DomainType.NONE :
                        MessageDefinition.DomainType.valueOf(matcher.group(3).substring(1)));
            }
        } else {
            definition.setDomain("");
            definition.setUsage("");
            definition.setDomainType(MessageDefinition.DomainType.NONE);
        }

        Map<String, Object> holder = this.getValueWithMap(yaml, "holder");
        Class<?> clazz = null;
        if (holder != null && holder.containsKey(kind.name())) {
            if (kind == MessageDefinition.Kind.subPayload) {
                Map<String, Object> subPayload = this.getValueWithMap(holder, kind.name());
                if (subPayload != null && subPayload.containsKey(identity)) {
                    clazz = ClassUtil.forName(this.getValueWithMap(subPayload, identity).toString());
                } else {
                    clazz = MessageHolder.class;
                }
            } else {
                clazz = ClassUtil.forName(this.getValueWithMap(holder, kind.name()).toString());
            }
        } else {
            clazz = MessageHolder.class;
        }
        definition.setHolder(clazz);

        return definition;
    }

    private <T> T getValueWithMap(Map<String, Object> map, String key) {
        return (T) map.get(key);
    }

    @Override
    protected List<Path> getSources() {
        return FileUtil.getClasspathFiles(this.classpath, "*." + this.type() + ".yml");
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
