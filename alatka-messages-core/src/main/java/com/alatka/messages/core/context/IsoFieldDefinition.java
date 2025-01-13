package com.alatka.messages.core.context;

import com.alatka.messages.core.domain.TLV2DomainParsed;
import com.alatka.messages.core.domain.TLVDomainParsed;
import com.alatka.messages.core.domain.TVDomainParsed;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 8583报文域定义类
 *
 * @author ybliu
 */
public class IsoFieldDefinition extends FieldDefinition {

    /**
     * 变长域长度域解析类型
     */
    private ParseType lenParseType;
    /**
     * {@link TVDomainParsed}
     * {@link TLVDomainParsed}
     * {@link TLV2DomainParsed}
     */
    private String aliasName;
    /**
     * 最大长度<br>
     * {@link #getFixed()} = true：{@link #getLength()} = {@link #getMaxLength()}<br>
     * {@link #getFixed()} = false：数据最大长度
     */
    private Integer maxLength;
    /**
     * 未配置子域异常
     */
    private Boolean nonSubdomainException = Boolean.TRUE;

    public ParseType getLenParseType() {
        return lenParseType;
    }

    public void setLenParseType(ParseType lenParseType) {
        this.lenParseType = lenParseType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getNonSubdomainException() {
        return nonSubdomainException;
    }

    public void setNonSubdomainException(Boolean nonSubdomainException) {
        this.nonSubdomainException = nonSubdomainException;
    }

    @Override
    public String toString() {
        return Stream.of("F" + getDomainNo(),
                        getAliasName(),
                        getName(),
                        getFixed() ? getLength().toString() : getLength() + "~" + getMaxLength(),
                        getRemark())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(":", "{", "}"));
    }

}
