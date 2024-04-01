package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.domain.DomainParsedFactory;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.util.ClassUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link FieldBuilder} 抽象类，实现序列化/反序列化方法，定义各个类型转换抽象方法
 *
 * @param <T> java type
 * @author ybliu
 * @see FieldBuilder
 */
public abstract class AbstractFieldBuilder<T> implements FieldBuilder {

    protected MessageDefinition messageDefinition;

    protected byte[] fromObjectToAscii(T value, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected byte[] fromObjectToBinary(T value, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected byte[] fromObjectToBcd(T value, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected byte[] fromObjectToEbcdic(T value, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected byte[] fromObjectToNone(T value, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected T toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected T toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected T toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected T toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    protected T toObjectWithNone(byte[] bytes, FieldDefinition fieldDefinition) {
        this.throwException(fieldDefinition);
        return null;
    }

    @Override
    public byte[] serialize(Object instance, FieldDefinition fieldDefinition) {
        try {
            // 1.获取instance属性值
            T value = this.getValue(instance, fieldDefinition);
            // 2.属性值转换为字节数组
            byte[] bytes = this.fromObject(value, fieldDefinition);
            // 3.字节数组包装成定义格式报文域
            byte[] result = DomainParsedFactory.getInstance(instance, this.messageDefinition, fieldDefinition)
                    .pack(bytes, fieldDefinition);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(messageDefinition + " -> " + fieldDefinition + "解析报错", e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes, FieldDefinition fieldDefinition, Object instance, AtomicInteger counter) {
        try {
            // 1.解析报文域字节数组
            byte[] valueBytes = DomainParsedFactory.getInstance(instance, this.messageDefinition, fieldDefinition)
                    .unpack(bytes, fieldDefinition, counter);
            // 2.报文域类型转换
            T value = this.toObject(valueBytes, fieldDefinition);
            // 3.属性赋值
            this.setValue(instance, fieldDefinition, value);

            return value;
        } catch (Exception e) {
            throw new RuntimeException(messageDefinition + " -> " + fieldDefinition + "解析报错", e);
        }
    }

    /**
     * java类型数据转换字节数组
     *
     * @param value           java类型数据
     * @param fieldDefinition {@link FieldDefinition}
     * @return 转换后字节数组
     */
    private byte[] fromObject(T value, FieldDefinition fieldDefinition) {
        byte[] bytes = new byte[0];
        if (value == null && returnIfNull()) {
            return bytes;
        }
        switch (fieldDefinition.getParseType()) {
            case ASCII:
                bytes = this.fromObjectToAscii(value, fieldDefinition);
                break;
            case BINARY:
                bytes = this.fromObjectToBinary(value, fieldDefinition);
                break;
            case BCD:
                bytes = this.fromObjectToBcd(value, fieldDefinition);
                break;
            case EBCDIC:
                bytes = this.fromObjectToEbcdic(value, fieldDefinition);
                break;
            case NONE:
            case NONE_V2:
                bytes = this.fromObjectToNone(value, fieldDefinition);
                break;
            default:
                throwException(fieldDefinition);
        }
        return bytes;
    }

    /**
     * 报文域字节数组转换java类型对象
     *
     * @param bytes           报文域字节数组
     * @param fieldDefinition {@link FieldDefinition}
     * @return 转化后java类型对象
     */
    private T toObject(byte[] bytes, FieldDefinition fieldDefinition) {
        if (bytes.length == 0) {
            return null;
        }
        T instance = null;
        switch (fieldDefinition.getParseType()) {
            case ASCII:
                instance = this.toObjectWithAscii(bytes, fieldDefinition);
                break;
            case BINARY:
                instance = this.toObjectWithBinary(bytes, fieldDefinition);
                break;
            case BCD:
                instance = this.toObjectWithBcd(bytes, fieldDefinition);
                break;
            case EBCDIC:
                instance = this.toObjectWithEbcdic(bytes, fieldDefinition);
                break;
            case NONE:
            case NONE_V2:
                instance = this.toObjectWithNone(bytes, fieldDefinition);
                break;
            default:
                throwException(fieldDefinition);
        }
        return instance;
    }

    /**
     * 根据{@link FieldDefinition}，获取数据对象属性值
     *
     * @param instance   取值对象
     * @param definition {@link FieldDefinition}
     * @return 值
     */
    private T getValue(Object instance, FieldDefinition definition) {
        if (instance instanceof MessageHolder) {
            return ((MessageHolder) instance).getByName(definition.getName());
        }
        return (T) ClassUtil.getFieldValue(instance, definition.getName());
    }

    /**
     * 根据{@link FieldDefinition}，赋值数据对象对应属性
     *
     * @param instance   被赋值对象
     * @param definition {@link FieldDefinition}
     * @param value      值
     */
    private void setValue(Object instance, FieldDefinition definition, T value) {
        if (instance instanceof MessageHolder) {
            ((MessageHolder) instance).put(definition, value);
        } else {
            ClassUtil.setFieldValue(instance, definition.getName(), value);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setMessageDefinition(MessageDefinition messageDefinition) {
        this.messageDefinition = messageDefinition;
    }

    protected boolean returnIfNull() {
        return true;
    }

    protected void throwException(FieldDefinition definition) {
        throw new IllegalArgumentException("illegal domainType: " + definition.getParseType()
                + " with class " + definition.getClazz());
    }

}
