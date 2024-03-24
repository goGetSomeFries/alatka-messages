package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;

/**
 * 固定格式报文打包/解包器
 *
 * @author ybliu
 */
class FixedMessageBuilder extends MessageBuilder {

    protected FixedMessageBuilder() {
    }

    @Override
    protected boolean filter(FieldDefinition fieldDefinition) {
        return true;
    }

    @Override
    protected void postProcess(FieldDefinition fieldDefinition, Object instance, Object value) {
        // do nothing
    }

}
