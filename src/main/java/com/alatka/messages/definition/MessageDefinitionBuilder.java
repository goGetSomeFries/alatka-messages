package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.message.MessageBuilder;

/**
 * 报文定义解析器<br>
 * 解析报文/报文域配置信息<br>
 *
 * @author ybliu
 * @see MessageBuilder
 */
public interface MessageDefinitionBuilder {

    /**
     * 构建报文配置信息到{@link MessageDefinitionContext}中
     */
    void build();

    /**
     * 动态刷新报文配置信息
     */
    void refresh();

}
