package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.FixedFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * 固定格式xml文件报文定义解析器
 *
 * @author ybliu
 * @see XmlMessageDefinitionBuilder
 * @see FileMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class FixedXmlMessageDefinitionBuilder extends XmlMessageDefinitionBuilder<FixedFieldDefinition> {

    public FixedXmlMessageDefinitionBuilder() {
        this("");
    }

    public FixedXmlMessageDefinitionBuilder(String classpath) {
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
