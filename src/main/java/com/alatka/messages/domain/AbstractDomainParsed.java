package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

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
     * @param definition
     * @return 结果字节数组
     */
    protected byte[] padding(byte[] bytes, FieldDefinition definition) {
        int paddingLength = definition.getLength() - bytes.length;
        if (paddingLength < 0) {
            throw new RuntimeException(definition + " actual length: " + bytes.length + ", expected length: " + definition.getLength());
        }
        if (paddingLength == 0) {
            return bytes;
        }
        byte[] padding = IntStream.range(0, paddingLength)
                .mapToObj(i -> this.fillBytes(definition))
                .reduce(new byte[0], BytesUtil::concat);

        return definition.getFieldType() == FieldDefinition.FieldType.NUMBER ?
                BytesUtil.concat(padding, bytes) : BytesUtil.concat(bytes, padding);
    }

    private byte[] fillBytes(FieldDefinition definition) {
        if (definition.getFieldType() == FieldDefinition.FieldType.NUMBER) {
            return this.isEbcdic(definition.getParseType()) ? BytesUtil.toEBCDIC("0".getBytes()) : "0".getBytes();

        }
        return this.isEbcdic(definition.getParseType()) ? BytesUtil.toEBCDIC(" ".getBytes()) : " ".getBytes();
    }

    private boolean isEbcdic(FieldDefinition.ParseType parseType) {
        return parseType == FieldDefinition.ParseType.BCD || parseType == FieldDefinition.ParseType.EBCDIC;
    }

}
