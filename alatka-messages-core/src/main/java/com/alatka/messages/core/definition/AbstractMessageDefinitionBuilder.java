package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.ClassAlias;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@link MessageDefinitionBuilder}抽象类
 *
 * @param <T> source
 * @author ybliu
 * @see MessageDefinitionBuilder
 */
public abstract class AbstractMessageDefinitionBuilder<T, S extends FieldDefinition> implements MessageDefinitionBuilder {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, Class<?>> classAliasMap;

    public AbstractMessageDefinitionBuilder() {
        this.classAliasMap = Arrays.stream(ClassAlias.values())
                .collect(HashMap::new, (k, v) -> k.put(v.name(), v.getClazz()), HashMap::putAll);
    }

    @Override
    public void build() {
        this.logger.debug("报文配置类{}开始执行...", this.getClass().getSimpleName());
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();

        List<MessageDefinition> messageDefinitions = new ArrayList<>();
        // MessageDefinition -> source 映射
        Map<MessageDefinition, T> mapping = this.getSources().stream()
                .peek(source -> this.logger.info("scan source: {}", source))
                .flatMap(source -> this.buildMessageDefinitions(source).stream()
                        .map(definition -> new Wrapper<>(definition, source)))
                .filter(wrapper -> wrapper.definition.isEnabled())
                .peek(wrapper -> this.logger.debug("build {}", wrapper.definition))
                .peek(wrapper -> messageDefinitions.add(wrapper.definition))
                .peek(wrapper -> {
                            if (!this.isTemplate(wrapper.definition)) {
                                // context 设置 MessageDefinition
                                context.messageDefinition(wrapper.definition.identity(), wrapper.definition);
                            }
                        }
                ).collect(Collectors.toMap(wrapper -> wrapper.definition, wrapper -> wrapper.object));

        // 模板对象，减少FieldDefinition对象创建，节省内存开销
        Map<String, List<S>> fieldDefinitionsTemplate = messageDefinitions.stream()
                .filter(this::isTemplate)
                .collect(Collectors.toMap(this::buildTemplateKey,
                        definition -> {
                            List<S> list = this.buildFieldDefinitions(definition, mapping.get(definition));
                            list.forEach(fieldDefinition -> this.postBuildFieldDefinition(definition, fieldDefinition));
                            return list;
                        }));

        // context 设置 List<FieldDefinition>
        messageDefinitions.stream()
                .filter(definition -> !isTemplate(definition))
                .map(definition -> {
                    List<FieldDefinition> fieldDefinitions =
                            this.buildFieldDefinitions(definition, mapping.get(definition), fieldDefinitionsTemplate);
                    return new Wrapper<>(definition, fieldDefinitions);
                })
                .peek(wrapper -> this.logger.debug("build {}: {}", wrapper.definition, wrapper.object))
                .forEach(wrapper -> context.fieldDefinitions(wrapper.definition, wrapper.object));
        this.logger.debug("报文配置类{}执行完成", this.getClass().getSimpleName());
    }

    @Override
    public void refresh() {
        this.build();
    }


    /**
     * 构造{@link FieldDefinition}集合
     *
     * @param definition {@link MessageDefinition}
     * @param source     数据源
     * @param templates  {@link MessageDefinition}模板对象
     * @return {@link FieldDefinition}集合
     */
    private List<FieldDefinition> buildFieldDefinitions(
            MessageDefinition definition, T source, Map<String, List<S>> templates) {
        List<S> list = templates.get(buildTemplateKey(definition));
        Map<S, S> map = list == null ? Collections.emptyMap()
                : list.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));

        // 如果模板对象存在，使用该对象，减少FieldDefinition对象创建
        return this.buildFieldDefinitions(definition, source).stream()
                .filter(fieldDefinition -> fieldDefinition.getStatus() != FieldDefinition.Status.CLOSE)
                .peek(fieldDefinition -> this.postBuildFieldDefinition(definition, fieldDefinition))
                .map(fieldDefinition -> map.getOrDefault(fieldDefinition, fieldDefinition))
                .map(FieldDefinition.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * 是否为模板配置
     *
     * @param definition {@link MessageDefinition}
     * @return 是否为模板配置
     */
    private boolean isTemplate(MessageDefinition definition) {
        return "template".equals(definition.getCode());
    }

    /**
     * 构建模板key
     *
     * @param definition {@link MessageDefinition}
     * @return 模板key
     */
    private String buildTemplateKey(MessageDefinition definition) {
        MessageDefinition template = definition.copy();
        template.setCode(null);
        return template.identity();
    }

    /**
     * {@link FieldDefinition} 后处理器
     *
     * @param definition {@link FieldDefinition}
     */
    private void postBuildFieldDefinition(MessageDefinition messageDefinition, FieldDefinition definition) {
        if (definition.getExistSubdomain() && definition.getClassName() == null) {
            switch (definition.getSubdomainType()) {
                case PAGE:
                    definition.setClassName(ClassAlias.List.name());
                    break;
                case ULV:
                case ULV2:
                case ULV3:
                case ULV4:
                case ULV5:
                case UV:
                case UVAS:
                    definition.setClassName(ClassAlias.UsageSubdomain.name());
                    break;
                default:
                    List<MessageDefinition> definitions =
                            MessageDefinitionContext.getInstance().childrenMessageDefinitions(messageDefinition, definition);
                    String className = definitions.isEmpty() ? ClassAlias.MessageHolder.name() : definitions.get(0).getHolder().getName();
                    definition.setClassName(className);
            }
        }
        if (definition.getClassType() == null) {
            String className = definition.getClassName();
            Class<?> classType = this.classAliasMap.containsKey(className) ?
                    this.classAliasMap.get(className) : ClassUtil.forName(className);
            definition.setClassType(classType);
        }
        if (definition.getClassType().isPrimitive()) {
            throw new IllegalArgumentException("fieldDefinition: " + definition + "不支持原始类型");
        }
    }

    /**
     * 根据数据源构建{@link MessageDefinition}集合<br>
     * source:messageDefinition 可为 1:1 或 1:N
     *
     * @param source 数据源
     * @return {@link MessageDefinition}集合
     */
    protected abstract List<MessageDefinition> buildMessageDefinitions(T source);

    /**
     * 根据数据源构造{@link FieldDefinition}集合
     *
     * @param definition {@link MessageDefinition}
     * @param source     数据源
     * @return {@link FieldDefinition}集合
     */
    protected abstract List<S> buildFieldDefinitions(MessageDefinition definition, T source);

    /**
     * 获取报文配置信息数据源：注解、数据库表、配置文件...
     *
     * @return 配置源集合
     */
    protected abstract List<T> getSources();

    /**
     * {@link MessageDefinition.Type}
     *
     * @return {@link MessageDefinition.Type}
     */
    protected abstract MessageDefinition.Type type();

    /**
     * source & {@link MessageDefinition} container
     */
    private class Wrapper<U> {

        private final MessageDefinition definition;
        private final U object;

        public Wrapper(MessageDefinition definition, U object) {
            this.definition = definition;
            this.object = object;
        }
    }

}
