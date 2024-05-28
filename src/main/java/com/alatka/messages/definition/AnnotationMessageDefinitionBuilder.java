package com.alatka.messages.definition;

import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.util.ClassUtil;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 注解报文定义解析器
 *
 * @author ybliu
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class AnnotationMessageDefinitionBuilder extends AbstractMessageDefinitionBuilder<Class<?>> {

    private final String packageName;

    public AnnotationMessageDefinitionBuilder(String packageName) {
        this.packageName = packageName;
    }

    @Override
    protected <S extends FieldDefinition> List<S> buildFieldDefinitions(MessageDefinition definition, Class<?> source) {
        List<Field> list = new ArrayList<>();
        ClassUtil.buildDeclaredFields(source, list);

        return list.stream()
                .filter(this::filter)
                .map(field -> new Wrapper(field, this.buildFieldDefinition(field)))
                .peek(wrapper -> this.postBuildFieldDefinition(wrapper.field, wrapper.fieldDefinition))
                .map(wrapper -> (S) wrapper.fieldDefinition)
                .sorted()
                .collect(Collectors.toList());
    }

    private void postBuildFieldDefinition(Field field, FieldDefinition fieldDefinition) {
        if (fieldDefinition.getExistSubdomain()) {
            Class<?> clazz = fieldDefinition.getClazz();
            if (UsageSubdomain.class == clazz) {
                Reflections reflections = new Reflections(this.packageName);
                Class<?> subdomainClass = ClassUtil.getGenericType(field);
                Map<String, MessageDefinition> messageDefinitionMap =
                        reflections.getSubTypesOf(subdomainClass).stream()
                                .map(MessageDefinitionContext.getInstance()::messageDefinition)
                                .collect(Collectors.toMap(MessageDefinition::getUsage, Function.identity()));
                fieldDefinition.setMessageDefinitionMap(messageDefinitionMap);
            } else {
                Class<?> resultClass = List.class.isAssignableFrom(clazz) ? ClassUtil.getGenericType(field) : clazz;
                MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(resultClass);
                Map<String, MessageDefinition> messageDefinitionMap = new HashMap<>(1);
                messageDefinitionMap.put(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);
                fieldDefinition.setMessageDefinitionMap(messageDefinitionMap);
            }
        }

        if (fieldDefinition.getParseType() == FieldDefinition.ParseType.ASCII &&
                (fieldDefinition.getExistSubdomain() || fieldDefinition.getClazz() == byte[].class)) {
            fieldDefinition.setParseType(FieldDefinition.ParseType.NONE);
        }
    }

    @Override
    protected List<MessageDefinition> buildMessageDefinitions(Class<?> source) {
        MessageDefinition definition = this.buildMessageDefinition(source);
        return Collections.singletonList(definition);
    }

    private MessageDefinition buildMessageDefinition(Class<?> clazz) {
        MessageMeta annotation = clazz.getAnnotation(MessageMeta.class);
        MessageDefinition definition = new MessageDefinition();
        definition.setType(annotation.type());
        definition.setGroup(annotation.group());
        definition.setCode(annotation.code());
        definition.setKind(annotation.kind());
        definition.setDomain(annotation.domain());
        definition.setUsage(annotation.usage());
        definition.setDomainType(annotation.domainType());
        definition.setHolder(annotation.customize() ? MessageHolder.class : clazz);
        definition.setCharset(annotation.charset());
        definition.setRemark(annotation.remark());
        return definition;
    }

    @Override
    protected List<Class<?>> getSources() {
        Reflections reflections = new Reflections(this.packageName);
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(MessageMeta.class, true);
        ArrayList<Class<?>> list = new ArrayList<>(set);
        return list.stream()
                .filter(clazz -> clazz.getAnnotation(MessageMeta.class).type() == this.type())
                .sorted(Comparator.comparing(this::buildMessageDefinition))
                .collect(Collectors.toList());
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("注解不支持动态加载");
    }

    /**
     * 构建{@link FieldDefinition}对象
     *
     * @param <S>   {@link FieldDefinition}
     * @param field {@link Field}
     * @return {@link FieldDefinition}
     */
    protected abstract <S extends FieldDefinition> S buildFieldDefinition(Field field);

    protected abstract boolean filter(Field field);

    private class Wrapper {
        private final Field field;
        private final FieldDefinition fieldDefinition;

        public Wrapper(Field field, FieldDefinition fieldDefinition) {
            this.field = field;
            this.fieldDefinition = fieldDefinition;
        }
    }

}
