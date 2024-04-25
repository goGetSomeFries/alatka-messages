package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * {@link DomainParsed}抽象类
 *
 * @author ybliu
 * @see DomainParsed
 */
public abstract class AbstractDomainParsed implements DomainParsed {

    /**
     * 报文域补字符
     *
     * @param bytes      报文域字节数组
     * @param definition {@link FieldDefinition}
     * @return 结果字节数组
     */
    protected byte[] padding(byte[] bytes, FieldDefinition definition) {
        int paddingLength = definition.getLength() - bytes.length;
        if (paddingLength < 0) {
            throw new IllegalArgumentException(definition + " actual length: " + bytes.length + ", expected length: " + definition.getLength());
        }
        if (paddingLength == 0) {
            return bytes;
        }
        byte[] padding = IntStream.range(0, paddingLength)
                .mapToObj(i -> this.fillBytes(definition))
                .reduce(new byte[0], BytesUtil::concat);

        return isNumberType(definition.getClazz()) ?
                BytesUtil.concat(padding, bytes) : BytesUtil.concat(bytes, padding);
    }

    private byte[] fillBytes(FieldDefinition definition) {
        switch (definition.getParseType()) {
            case EBCDIC:
                return isNumberType(definition.getClazz()) ? BytesUtil.toEBCDIC("0") : BytesUtil.toEBCDIC(" ");
            case BCD:
                return BytesUtil.intToBytes(0);
            default:
                return isNumberType(definition.getClazz()) ? "0".getBytes() : " ".getBytes();
        }
    }

    /**
     * 数值类型
     *
     * @param clazz {@link FieldDefinition#getClazz()}
     * @return 是否数值类型
     */
    private boolean isNumberType(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz)
                || Date.class.isAssignableFrom(clazz)
                || TemporalAccessor.class.isAssignableFrom(clazz);
    }

}
