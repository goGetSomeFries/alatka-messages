package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinition;

import javax.sql.DataSource;

/**
 * 固定格式database报文定义解析器
 *
 * @author ybliu
 * @see DatabaseMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class FixedDatabaseMessageDefinitionBuilder extends DatabaseMessageDefinitionBuilder {

    public FixedDatabaseMessageDefinitionBuilder(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.fixed;
    }

}
