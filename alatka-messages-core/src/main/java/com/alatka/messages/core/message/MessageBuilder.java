package com.alatka.messages.core.message;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.domain.DomainParsed;
import com.alatka.messages.core.domain.DomainParsedFactory;
import com.alatka.messages.core.field.FieldBuilder;
import com.alatka.messages.core.field.FieldBuilderFactory;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.util.BytesUtil;
import com.alatka.messages.core.util.ClassUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 报文打包/解包器
 *
 * @author ybliu
 */
public abstract class MessageBuilder {

    protected MessageDefinition definition;

    public static <T> MessageBuilder init(Class<T> clazz) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition definition = context.messageDefinition(clazz);
        return init(definition);
    }

    public static MessageBuilder init(String key) {
        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition definition = context.messageDefinition(key);
        if (definition == null) {
            throw new IllegalArgumentException("key [" + key + "] not exist");
        }
        return init(definition);
    }

    /**
     * 支持8583、固定格式解析<br>
     * 支持8583各类子域单独解析<br>
     * 不支持分页子域、8583不定长子域单独解析
     *
     * @param definition {@link MessageDefinition}
     * @return {@link MessageBuilder}
     */
    public static MessageBuilder init(MessageDefinition definition) {
        if (MessageDefinition.Type.iso == definition.getType() && definition.getKind() == MessageDefinition.Kind.payload) {
            return new IsoMessageBuilder(definition);
        }
        if (definition.getDomainType() == MessageDefinition.DomainType.TV) {
            return new TVSubdomainMessageBuilder(definition);
        }
        if (definition.getDomainType() == MessageDefinition.DomainType.TLV) {
            return new TLVSubdomainMessageBuilder(definition);
        }
        if (definition.getDomainType() == MessageDefinition.DomainType.TLV2) {
            return new TLV2SubdomainMessageBuilder(definition);
        }
        if (definition.getDomainType() == MessageDefinition.DomainType.BITMAP) {
            return new BitmapSubdomainMessageBuilder(definition);
        }
        return new DefaultMessageBuilder(definition);
    }

    public <T> byte[] pack(T instance) {
        List<FieldDefinition> fieldDefinitions = this.buildFieldDefinitions();

        byte[] result = fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> {
                    try {
                        return this.doPack(instance, definition, fieldDefinition);
                    } catch (Exception e) {
                        throw new RuntimeException(definition + " -> " + fieldDefinition + "解析报错", e);
                    }
                })
                .peek(wrapper -> this.postProcess(wrapper.fieldDefinition, wrapper.value, true))
                .map(wrapper -> (byte[]) wrapper.value)
                .reduce(new byte[0], this::concatBytes);
        this.postProcess();
        return result;
    }

    protected <T> Wrapper doPack(T instance, MessageDefinition definition, FieldDefinition fieldDefinition) {
        // 对象解析为字节数组
        FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
        byte[] bytes = fieldBuilder.serialize(instance, fieldDefinition);

        // 字节数组包装成定义格式报文域
        DomainParsed domainParsed = DomainParsedFactory.getInstance(instance, definition, fieldDefinition);
        byte[] packed = domainParsed.pack(bytes, fieldDefinition);

        return new Wrapper(packed, fieldDefinition);
    }

    public <T> T unpack(String hexStr) {
        byte[] bytes = BytesUtil.hexToBytes(hexStr);
        return this.unpack(bytes);
    }

    @SuppressWarnings("unchecked")
    public <T> T unpack(byte[] bytes) {
        // MessageHolder or POJO
        Object instance = definition.getHolder() == MessageHolder.class ?
                MessageHolder.newInstance(definition.identity()) : ClassUtil.newInstance(definition.getHolder());
        AtomicInteger counter = new AtomicInteger(0);

        List<FieldDefinition> fieldDefinitions = this.buildFieldDefinitions(bytes);
        fieldDefinitions.stream()
                .filter(this::filter)
                .map(fieldDefinition -> {
                    try {
                        return this.doUnpack(instance, definition, fieldDefinition, bytes, counter);
                    } catch (Exception e) {
                        throw new RuntimeException(definition + " -> " + fieldDefinition + "解析报错", e);
                    }
                }).forEach(wrapper -> this.postProcess(wrapper.fieldDefinition, wrapper.value, false));
        this.postProcess();
        return (T) instance;
    }

    protected <T> Wrapper doUnpack(T instance, MessageDefinition definition,
                                   FieldDefinition fieldDefinition, byte[] bytes, AtomicInteger counter) {
        // 解析报文域字节数组
        DomainParsed domainParsed = DomainParsedFactory.getInstance(instance, definition, fieldDefinition);
        byte[] valueBytes = domainParsed.unpack(bytes, fieldDefinition, counter);

        // 字节数组解析为对象
        FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
        Object value = fieldBuilder.deserialize(valueBytes, fieldDefinition, instance, counter);
        return new Wrapper(value, fieldDefinition);
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
     * @return 是否过滤
     */
    protected boolean filter(FieldDefinition fieldDefinition) {
        return true;
    }

    /**
     * 后处理
     *
     * @param fieldDefinition {@link FieldDefinition}
     * @param value           字段值
     * @param packed          打包/解包
     */
    protected void postProcess(FieldDefinition fieldDefinition, Object value, boolean packed) {
    }

    protected void postProcess() {
    }

    protected class Wrapper {
        private final Object value;
        private final FieldDefinition fieldDefinition;

        public Wrapper(Object value, FieldDefinition fieldDefinition) {
            this.value = value;
            this.fieldDefinition = fieldDefinition;
        }
    }
}
