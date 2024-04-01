package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@link MessageDefinitionBuilder}抽象类
 *
 * @param <T> source
 * @author ybliu
 * @see MessageDefinitionBuilder
 */
public abstract class AbstractMessageDefinitionBuilder<T> implements MessageDefinitionBuilder {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void build() {
        this.logger.debug("报文配置{}加载开始执行...", this.getClass().getSimpleName());
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();

        List<MessageDefinition> messageDefinitions = new ArrayList<>();
        // MessageDefinition -> source 映射
        Map<MessageDefinition, T> mapping = this.getSources().stream()
                .peek(source -> this.logger.info("scan source: " + source))
                .flatMap(source -> this.buildMessageDefinitions(source).stream()
                        .peek(this::postBuildMessageDefinition)
                        .map(definition -> new Wrapper(definition, source)))
                .peek(wrapper -> messageDefinitions.add(wrapper.definition))
                .peek(wrapper -> {
                            if (!this.isTemplate(wrapper.definition)) {
                                // context 设置 MessageDefinition
                                context.messageDefinition(wrapper.definition.identity(), wrapper.definition);
                            }
                        }
                ).collect(Collectors.toMap(wrapper -> wrapper.definition, wrapper -> wrapper.source));

        // 模板对象，减少FieldDefinition对象创建，节省内存开销
        Map<String, List<FieldDefinition>> fieldDefinitionsTemplate = messageDefinitions.stream()
                .filter(this::isTemplate)
                .collect(Collectors.toMap(this::buildTemplateKey,
                        definition -> {
                            List<FieldDefinition> list = this.buildFieldDefinitions(definition, mapping.get(definition));
                            list.forEach(this::postBuildFieldDefinition);
                            return list;
                        }));

        // context 设置 List<FieldDefinition>
        messageDefinitions.stream()
                .filter(definition -> !isTemplate(definition))
                .forEach(definition ->
                        context.fieldDefinitions(definition,
                                buildFieldDefinitions(definition, mapping.get(definition), fieldDefinitionsTemplate)));
        this.logger.debug("报文配置{}加载执行完成", this.getClass().getSimpleName());
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
    private <S extends FieldDefinition> List<S> buildFieldDefinitions(
            MessageDefinition definition, T source, Map<String, List<S>> templates) {
        List<S> list = templates.get(buildTemplateKey(definition));
        Map<S, S> map = list == null ? Collections.emptyMap()
                : list.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));

        // 如果模板对象存在，使用该对象，减少FieldDefinition对象创建
        return this.buildFieldDefinitions(definition, source).stream()
                .peek(this::postBuildFieldDefinition)
                .map(fieldDefinition -> map.getOrDefault(fieldDefinition, (S) fieldDefinition))
                .collect(Collectors.toList());
    }

    /**
     * {@link MessageDefinition}后处理器
     *
     * @param definition {@link MessageDefinition}
     */
    private void postBuildMessageDefinition(MessageDefinition definition) {
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
    protected void postBuildFieldDefinition(FieldDefinition definition) {
        if (definition.getClazz().isPrimitive()) {
            throw new IllegalArgumentException("fieldDefinition: " + definition + "不支持原始类型");
        }
        if (definition.getStatus() == null) {
            definition.setStatus(1);
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
     * @param <S>        {@link FieldDefinition}
     * @param definition {@link MessageDefinition}
     * @param source     数据源
     * @return {@link FieldDefinition}集合
     */
    protected abstract <S extends FieldDefinition> List<S> buildFieldDefinitions(MessageDefinition definition, T source);

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
    private class Wrapper {

        private final MessageDefinition definition;
        private final T source;

        public Wrapper(MessageDefinition definition, T source) {
            this.definition = definition;
            this.source = source;
        }
    }

}
