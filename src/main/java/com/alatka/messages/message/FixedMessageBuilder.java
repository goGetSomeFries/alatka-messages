package com.alatka.messages.message;

import com.alatka.messages.context.MessageDefinition;

/**
 * 固定格式报文打包/解包器
 *
 * @author ybliu
 */
public class FixedMessageBuilder extends MessageBuilder {

    public FixedMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

}
