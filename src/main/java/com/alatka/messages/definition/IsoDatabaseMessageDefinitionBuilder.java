package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinition;

import javax.sql.DataSource;

/**
 * 8583 database报文定义解析器
 *
 * @author ybliu
 * @see DatabaseMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class IsoDatabaseMessageDefinitionBuilder extends DatabaseMessageDefinitionBuilder {

    public IsoDatabaseMessageDefinitionBuilder(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.iso;
    }
}
