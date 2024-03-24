package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 报文域类型解析器抽象类
 *
 * @author ybliu
 * @see DomainParsed
 */
public abstract class AbstractDomainParsed implements DomainParsed {

    protected MessageDefinition messageDefinition;

    @Override
    public void setMessageDefinition(MessageDefinition messageDefinition) {
        this.messageDefinition = messageDefinition;
    }

    /**
     * 报文域补字符
     *
     * @param bytes      报文域字节数组
     * @param length     报文域长度
     * @param fieldType  {@link FieldDefinition.FieldType}
     * @param definition
     * @return 结果字节数组
     */
    protected byte[] padding(byte[] bytes, int length, FieldDefinition.FieldType fieldType, FieldDefinition definition) {
        int paddingLength = length - bytes.length;
        if (paddingLength < 0) {
            throw new RuntimeException(messageDefinition + " -> " + definition + " actual length: " + bytes.length + ", expected length: " + length);
        }
        if (paddingLength == 0) {
            return bytes;
        }
        String padding = IntStream.range(0, paddingLength)
                .mapToObj(i -> fieldType.getFillChar())
                .map(fillChar -> Optional.ofNullable(fillChar).orElse(""))
                .collect(Collectors.joining(""));

        if (FieldDefinition.FieldType.DIRECTION_L.equals(fieldType.getDirection())) {
            return BytesUtil.concat(padding.getBytes(), bytes);
        } else if (FieldDefinition.FieldType.DIRECTION_R.equals(fieldType.getDirection())) {
            return BytesUtil.concat(bytes, padding.getBytes());
        } else {
            return bytes;
        }
    }

}
