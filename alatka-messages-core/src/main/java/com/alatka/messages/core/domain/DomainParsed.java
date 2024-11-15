package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.field.FieldBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 报文域解析器<br>
 * 线程不安全
 *
 * @author ybliu
 * @see FieldBuilder
 */
public interface DomainParsed {

    /**
     * 报文域类型解析器优先级；数值越大，优先级越高<br>
     *
     * @return 优先级数值
     */
    int getOrder();

    /**
     * 是否匹配报文域类型解析器
     *
     * @param messageDefinition {@link MessageDefinition}
     * @param fieldDefinition   {@link FieldDefinition}
     * @return 是否匹配
     */
    boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition);

    /**
     * 打包
     *
     * @param bytes           报文域字节数组
     * @param fieldDefinition {@link FieldDefinition}
     * @return 结果字节数组
     */
    byte[] pack(byte[] bytes, FieldDefinition fieldDefinition);

    /**
     * 解包
     *
     * @param bytes           报文字节数组
     * @param fieldDefinition {@link FieldDefinition}
     * @param counter         计数器，计算字节位置
     * @return 结果字节数组
     */
    byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter);
}
