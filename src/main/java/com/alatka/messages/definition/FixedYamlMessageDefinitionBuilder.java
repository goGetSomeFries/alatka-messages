package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.FixedFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * 固定格式yaml文件报文定义解析器
 *
 * @author ybliu
 * @see YamlMessageDefinitionBuilder
 * @see FileMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class FixedYamlMessageDefinitionBuilder extends YamlMessageDefinitionBuilder<FixedFieldDefinition> {

    public FixedYamlMessageDefinitionBuilder() {
        this("");
    }

    public FixedYamlMessageDefinitionBuilder(String classpath) {
        super(classpath);
    }

    @Override
    protected void postBuildFieldDefinition(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        // do nothing
    }

    @Override
    protected Class<FixedFieldDefinition> fieldDefinitionClass() {
        return FixedFieldDefinition.class;
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.fixed;
    }
}
