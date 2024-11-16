package com.alatka.messages.core.message;

import com.alatka.messages.core.context.MessageDefinition;

/**
 * 默认报文打包/解包器
 *
 * @author ybliu
 */
public class DefaultMessageBuilder extends MessageBuilder {

    public DefaultMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }
}
