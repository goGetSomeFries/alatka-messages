package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.domain.DomainParsed;
import com.alatka.messages.domain.DomainParsedFactory;
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
        if (definition == null) {
            throw new IllegalArgumentException("key [" + key + "] not exist");
        }
        return init(definition);
    }

    public static <S extends MessageBuilder> S init(MessageDefinition definition) {
        MessageBuilder messageBuilder =
                definition.getType() == MessageDefinition.Type.fixed ?
                        new FixedMessageBuilder() : new IsoMessageBuilder();
        messageBuilder.definition = definition;
        return (S) messageBuilder;
    }

    public <T> byte[] pack(T instance) {
        List<FieldDefinition> fieldDefinitions = this.buildFieldDefinitions();

        byte[] result = fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> this.doPack(instance, definition, fieldDefinition))
                .peek(wrapper -> this.postProcess(wrapper.fieldDefinition, instance, wrapper.value, true))
                .map(wrapper -> (byte[]) wrapper.value)
                .reduce(new byte[0], this::concatBytes);
        this.postProcess();
        return result;
    }

    protected <T> Wrapper doPack(T instance, MessageDefinition definition, FieldDefinition fieldDefinition) {
        try {
            // 对象解析为字节数组
            FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
            byte[] bytes = fieldBuilder.serialize(instance, fieldDefinition);

            // 字节数组包装成定义格式报文域
            DomainParsed domainParsed = DomainParsedFactory.getInstance(instance, definition, fieldDefinition);
            byte[] packed = domainParsed.pack(bytes, fieldDefinition);

            return new Wrapper(packed, fieldDefinition);
        } catch (Exception e) {
            throw new RuntimeException(definition + " -> " + fieldDefinition + "解析报错", e);
        }
    }

    public <T> T unpack(String hexStr) {
        byte[] bytes = BytesUtil.hexToBytes(hexStr);
        return this.unpack(bytes);
    }

    public <T> T unpack(byte[] bytes) {
        Object instance = definition.getHolder() == MessageHolder.class ?
                MessageHolder.newInstance(definition.identity()) : ClassUtil.newInstance(definition.getHolder());
        AtomicInteger counter = new AtomicInteger(0);

        List<FieldDefinition> fieldDefinitions = this.buildFieldDefinitions(bytes);
        fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> this.doUnpack(instance, definition, fieldDefinition, bytes, counter))
                .forEach(wrapper -> this.postProcess(wrapper.fieldDefinition, instance, wrapper.value, false));
        this.postProcess();
        return (T) instance;
    }

    protected <T> Wrapper doUnpack(T instance, MessageDefinition definition,
                                   FieldDefinition fieldDefinition, byte[] bytes, AtomicInteger counter) {
        try {
            // 解析报文域字节数组
            DomainParsed domainParsed = DomainParsedFactory.getInstance(instance, definition, fieldDefinition);
            byte[] valueBytes = domainParsed.unpack(bytes, fieldDefinition, counter);

            // 字节数组解析为对象
            FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
            Object value = fieldBuilder.deserialize(valueBytes, fieldDefinition, instance, counter);
            return new Wrapper(value, fieldDefinition);
        } catch (Exception e) {
            throw new RuntimeException(definition + " -> " + fieldDefinition + "解析报错", e);
        }
    }

    protected byte[] concatBytes(byte[] bytes1, byte[] bytes2) {
        return BytesUtil.concat(bytes1, bytes2);
    }

    protected List<FieldDefinition> buildFieldDefinitions() {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        return context.fieldDefinitions(definition);
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
    protected boolean filter(FieldDefinition fieldDefinition) {
        return true;
    }

    /**
     * 后处理
     *
     * @param fieldDefinition {@link FieldDefinition}
     * @param instance
     * @param value
     * @param packed
     */
    protected void postProcess(FieldDefinition fieldDefinition, Object instance, Object value, boolean packed) {
    }

    protected void postProcess() {
    }

    class Wrapper {
        private final Object value;
        private final FieldDefinition fieldDefinition;

        public Wrapper(Object value, FieldDefinition fieldDefinition) {
            this.value = value;
            this.fieldDefinition = fieldDefinition;
        }
    }
}
