package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.message.MessageBuilder;

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
