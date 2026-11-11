package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 报文域字段解析器
 *
 * @author ybliu
 */
public interface FieldBuilder {

    /**
     * 序列化
     *
     * @param instance        被序列化对象
     * @param fieldDefinition {@link FieldDefinition}
     * @return 序列化字节数组
     */
    byte[] serialize(Object instance, FieldDefinition fieldDefinition);

    /**
     * 反序列化
     *
     * @param bytes           报文字节数组
     * @param fieldDefinition {@link FieldDefinition}
     * @param instance        反序列化结果对象
     * @param counter         计数器，计算字节位置
     * @return 反序列化对象
     */
    Object deserialize(byte[] bytes, FieldDefinition fieldDefinition, Object instance, AtomicInteger counter);

    /**
     * 报文域解析器优先级；数值越大，优先级越高
     *
     * @return 优先级数值
     */
    int getOrder();

    /**
     * 是否匹配解析器
     *
     * @param messageDefinition {@link MessageDefinition}
     * @param definition        {@link FieldDefinition}
     * @return 是否匹配
     */
    boolean matched(MessageDefinition messageDefinition, FieldDefinition definition);

    /**
     * 针对 JSON 字符串转换的 Map<String, Object> ，将其 Value 默认类型转换为 {@link FieldDefinition#getClassType()}；
     *
     * @param value           {@link Map.Entry#getValue()}
     * @param fieldDefinition {@link FieldDefinition}
     * @return {@link FieldDefinition#getClassType()} 类型对应的值
     */
    Object normalizeType(Object value, FieldDefinition fieldDefinition);

    /**
     * 与 {@link #normalizeType(Object, FieldDefinition)} 相对，
     * 将 {@link FieldDefinition#getClassType()} 类型的值格式化 JSON 字符串正确显示
     *
     * @param value           {@link FieldDefinition#getClassType()} 类型值
     * @param fieldDefinition {@link FieldDefinition}
     * @return 格式化后的类型
     */
    Object denormalizeType(Object value, FieldDefinition fieldDefinition);

    /**
     * 设置 {@link MessageDefinition}
     *
     * @param messageDefinition {@link MessageDefinition}
     */
    @Deprecated
    default void setMessageDefinition(MessageDefinition messageDefinition) {
    }

}
