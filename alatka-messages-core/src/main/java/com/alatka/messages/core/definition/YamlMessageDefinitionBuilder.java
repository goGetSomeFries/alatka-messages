package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.support.FileWrapper;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.support.Constant;
import com.alatka.messages.core.util.ClassUtil;
import com.alatka.messages.core.util.YamlUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * yaml文件报文定义解析器
 *
 * @author ybliu
 * @see FileMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class YamlMessageDefinitionBuilder<S extends FieldDefinition> extends FileMessageDefinitionBuilder<S> {

    /**
     * yaml root element
     */
    private static final String YAML_ROOT_NAME = "alatka.messages";

    /**
     * 子域key Pattern<br>
     * e.g. F61$SM@TLV F59$SM_F3 F60@TLV
     */
    private static final Pattern PATTERN = Pattern.compile("^([$\\w]+?)(\\$[0-9A-Z]{2})?(@\\w+)?$");

    public YamlMessageDefinitionBuilder(String classpath) {
        super(classpath);
    }

    @Override
    protected List<Map<String, Object>> doBuildFieldDefinitions(MessageDefinition definition, FileWrapper source) {
        Map<String, Object> yaml = YamlUtil.getMap(source, YAML_ROOT_NAME, Object.class);
        Map<String, Object> message = this.getValueWithMap(yaml, "message");
        MessageDefinition.Kind kind = definition.getKind();

        if (kind == MessageDefinition.Kind.subPayload) {
            String domain = definition.getDomain();
            String usage = definition.getUsage().isEmpty() ? "" : "$".concat(definition.getUsage());
            String domainType = definition.getDomainType() == MessageDefinition.DomainType.NONE ?
                    "" : "@".concat(definition.getDomainType().name());

            Map<String, Object> map = this.getValueWithMap(message, kind.name());
            return this.getValueWithMap(map, domain.concat(usage).concat(domainType));
        }
        return this.getValueWithMap(message, kind.name());
    }

    @Override
    protected List<MessageDefinition> buildMessageDefinitions(FileWrapper source) {
        Map<String, Object> yaml = YamlUtil.getMap(source, YAML_ROOT_NAME, Object.class);
        Map<String, Object> message = this.getValueWithMap(yaml, "message");

        return Arrays.stream(MessageDefinition.Kind.values())
                .filter(kind -> message.containsKey(kind.name()))
                .flatMap(kind -> {
                    if (kind == MessageDefinition.Kind.subPayload) {
                        Map<String, Object> subPayload = this.getValueWithMap(message, kind.name());
                        return subPayload.keySet().stream()
                                .map(identity -> buildMessageDefinition(kind, identity, yaml));
                    }
                    return Stream.of(buildMessageDefinition(kind, null, yaml));
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
        definition.setCharset(yaml.get("charset") == null ? Constant.DEFAULT_CHARSET : yaml.get("charset").toString());
        definition.setRemark(yaml.get("remark") == null ? "" : yaml.get("remark").toString());
        definition.setEnabled(yaml.get("enabled") == null ? true : (boolean) yaml.get("enabled"));

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

    @Override
    protected String fileSuffix() {
        return ".yml";
    }
}
