package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.util.ClassUtil;

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

    @Override
    public byte[] serialize(Object instance, FieldDefinition fieldDefinition) {
        // 1.获取instance属性值
        T value = this.getValue(instance, fieldDefinition);
        // 2.属性值转换为字节数组
        byte[] bytes = this.fromObject(value, fieldDefinition);

        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes, FieldDefinition fieldDefinition, Object instance, AtomicInteger counter) {
        // 1.报文域类型转换
        T value = this.toObject(bytes, fieldDefinition);
        // 2.属性赋值
        this.setValue(instance, fieldDefinition, value);

        return value;
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
                bytes = this.fromObjectToNone(value, fieldDefinition);
                break;
            default:
                throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                        + " with class " + fieldDefinition.getClassType());
        }
        return bytes;
    }

    /**
     * 报文域字节数组转换java类型对象<br>
     * 对象类型分三种情况：<br>
     * 1.该域不包括子域，则为普通java类型或自定义类型<br>
     * 2.该域包括子域，非usage子域类型，则为MessageHolder或POJO<br>
     * 3.该域包括子域，是usage子域类型，则为UsageSubdomain<MessageHolder>或UsageSubdomain<POJO>
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
                instance = this.toObjectWithNone(bytes, fieldDefinition);
                break;
            default:
                throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                        + " with class " + fieldDefinition.getClassType());
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
    @SuppressWarnings("unchecked")
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

    protected byte[] fromObjectToAscii(T value, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected byte[] fromObjectToBinary(T value, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected byte[] fromObjectToBcd(T value, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected byte[] fromObjectToEbcdic(T value, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected byte[] fromObjectToNone(T value, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected T toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected T toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected T toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected T toObjectWithEbcdic(byte[] bytes, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
    }

    protected T toObjectWithNone(byte[] bytes, FieldDefinition fieldDefinition) {
        throw new IllegalArgumentException("illegal domainType: " + fieldDefinition.getParseType()
                + " with class " + fieldDefinition.getClassType());
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

}
