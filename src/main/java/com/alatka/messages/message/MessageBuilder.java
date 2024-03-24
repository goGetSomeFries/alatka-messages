package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.field.FieldBuilder;
import com.alatka.messages.field.FieldBuilderFactory;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 报文打包/解包器
 *
 * @author ybliu
 * @see IsoMessageBuilder
 * @see IsoTLVMessageBuilder
 * @see FixedMessageBuilder
 */
public abstract class MessageBuilder {

    protected MessageDefinition definition;

    protected MessageBuilder() {
    }

    public static <T, S extends MessageBuilder> S init(Class<T> clazz) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition definition = context.messageDefinition(clazz);
        return init(definition);
    }

    public static <S extends MessageBuilder> S init(String key) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition definition = context.messageDefinition(key);
        return init(definition);
    }

    public static <S extends MessageBuilder> S init(MessageDefinition definition) {
        MessageBuilder messageBuilder =
                definition.getType() == MessageDefinition.Type.fixed ?
                        new FixedMessageBuilder() :
                        definition.getDomainType() == MessageDefinition.DomainType.TLV ?
                                new IsoTLVMessageBuilder() : new IsoMessageBuilder();
        messageBuilder.definition = definition;
        return (S) messageBuilder;
    }

    public <T> byte[] pack(T instance) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        List<FieldDefinition> fieldDefinitions = context.fieldDefinitions(definition);

        return fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> {
                    FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
                    byte[] bytes = fieldBuilder.serialize(instance, fieldDefinition);
                    return new Wrapper(bytes, fieldDefinition);
                })
                .peek(wrapper -> this.postProcess(wrapper.fieldDefinition, instance, wrapper.value))
                .map(wrapper -> (byte[]) wrapper.value)
                .reduce(new byte[0], BytesUtil::concat);
    }

    public <T> T unpack(String hexStr) {
        byte[] bytes = BytesUtil.hexToBytes(hexStr);
        return this.unpack(bytes);
    }

    public <T> T unpack(byte[] bytes) {
        return this.unpack(bytes, true);
    }

    public <T> T unpack(byte[] bytes, boolean outOfBounds) {
        Object instance = definition.getHolder() == MessageHolder.class ?
                MessageHolder.newInstance(definition.identity()) : ClassUtil.newInstance(definition.getHolder());
        AtomicInteger counter = new AtomicInteger(0);

        List<FieldDefinition> fieldDefinitions = this.buildFieldDefinitions(bytes);
        fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> {
                    FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
                    if (outOfBounds && counter.get() >= bytes.length) {
                        throw new RuntimeException("字节长度超出范围");
                    }
                    Object value = !outOfBounds && counter.get() >= bytes.length ? null : fieldBuilder.deserialize(bytes, fieldDefinition, instance, counter);
                    return new Wrapper(value, fieldDefinition);
                })
                .forEach(wrapper -> this.postProcess(wrapper.fieldDefinition, instance, wrapper.value));
        return (T) instance;
    }

    protected List<FieldDefinition> buildFieldDefinitions(byte[] bytes) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        return context.fieldDefinitions(definition);
    }

    /**
     * {@link FieldDefinition} 过滤器
     *
     * @param fieldDefinition {@link FieldDefinition}
     * @return
     */
    protected abstract boolean filter(FieldDefinition fieldDefinition);

    /**
     * 后处理
     *
     * @param fieldDefinition {@link FieldDefinition}
     * @param instance
     * @param value
     */
    protected abstract void postProcess(FieldDefinition fieldDefinition, Object instance, Object value);

    private class Wrapper {
        private final Object value;
        private final FieldDefinition fieldDefinition;

        public Wrapper(Object value, FieldDefinition fieldDefinition) {
            this.value = value;
            this.fieldDefinition = fieldDefinition;
        }
    }
}
