package com.alatka.messages.definition;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.List;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class DatabaseMessageDefinitionBuilder extends AbstractMessageDefinitionBuilder<Object> {

    @Override
    protected List<MessageDefinition> buildMessageDefinitions(Object source) {
        return null;
    }

    @Override
    protected <S extends FieldDefinition> List<S> buildFieldDefinitions(MessageDefinition definition, Object source) {
        return null;
    }

    @Override
    protected List<Object> getSources() {
        return null;
    }

    @Override
    protected MessageDefinition.Type type() {
        return null;
    }
}
