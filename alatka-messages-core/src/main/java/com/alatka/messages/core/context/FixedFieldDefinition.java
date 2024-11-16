package com.alatka.messages.core.context;

/**
 * 固定格式报文域定义类
 *
 * @author ybliu
 */
public class FixedFieldDefinition extends FieldDefinition {

    @Override
    public String toString() {
        return "{" + String.join(":",
                "F" + getDomainNo(),
                getName(),
                getLength().toString(),
                getRemark())
                + "}";
    }

}