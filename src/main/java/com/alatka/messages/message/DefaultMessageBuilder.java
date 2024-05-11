package com.alatka.messages.message;

import com.alatka.messages.context.MessageDefinition;

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
