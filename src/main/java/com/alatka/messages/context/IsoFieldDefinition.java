package com.alatka.messages.context;

import com.alatka.messages.domain.TVDomainParsed;

/**
 * 8583报文域定义类
 *
 * @author ybliu
 */
public class IsoFieldDefinition extends FieldDefinition {

    /**
     * {@link TVDomainParsed}
     */
    private String aliasName;
    /**
     * 最大长度<br>
     * {@link IsoFieldDefinition#getFixed()} = true：{@link IsoFieldDefinition#getLength()} = {@link IsoFieldDefinition#maxLength}<br>
     * {@link IsoFieldDefinition#getFixed()} = false：数据最大长度
     */
    private Integer maxLength;

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

    @Override
    public String toString() {
        return "{" + String.join(":",
                "F" + getDomainNo(),
                getName(),
                getFixed() ? getLength().toString() : getLength() + "~" + getMaxLength(),
                getRemark())
                + "}";
    }

}
