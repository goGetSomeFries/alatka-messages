package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

/**
 * 8583 yaml文件报文定义解析器
 *
 * @author ybliu
 * @see YamlMessageDefinitionBuilder
 * @see FileMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class IsoYamlMessageDefinitionBuilder extends YamlMessageDefinitionBuilder<IsoFieldDefinition> {

    public IsoYamlMessageDefinitionBuilder() {
        this(DEFAULT_CLASSPATH);
    }

    public IsoYamlMessageDefinitionBuilder(String classpath) {
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
    protected Class<IsoFieldDefinition> fieldDefinitionClass() {
        return IsoFieldDefinition.class;
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.iso;
    }
}
