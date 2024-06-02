package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * 8583 xml文件报文定义解析器
 *
 * @author ybliu
 * @see XmlMessageDefinitionBuilder
 * @see FileMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class IsoXmlMessageDefinitionBuilder extends XmlMessageDefinitionBuilder {

    public IsoXmlMessageDefinitionBuilder() {
        this("");
    }

    public IsoXmlMessageDefinitionBuilder(String classpath) {
        super(classpath);
    }

    @Override
    protected void postBuildFieldDefinition(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        IsoFieldDefinition definition = (IsoFieldDefinition) fieldDefinition;
        if (definition.getLength() == null) {
            definition.setLength(0);
        }
        if (definition.getFixed() && definition.getMaxLength() == null) {
            definition.setMaxLength(definition.getLength());
        }
    }

    @Override
    protected Class<?> fieldDefinitionClass() {
        return IsoFieldDefinition.class;
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.iso;
    }
}
