package com.alatka.messages.core.support;

import com.alatka.messages.core.context.MessageDefinition;

public class ExecutionContext {

    private ThreadLocal<Object> messageHolderContext = new ThreadLocal<>();

    private ThreadLocal<MessageDefinition> messageDefinitionContext = new ThreadLocal<>();

    private ExecutionContext() {
    }

    public void setMessageHolder(Object messageHolder) {
        this.messageHolderContext.set(messageHolder);
    }

    public Object getMessageHolder() {
        return messageHolderContext.get();
    }

    public void setMessageDefinition(MessageDefinition messageDefinition) {
        this.messageDefinitionContext.set(messageDefinition);
    }

    public MessageDefinition getMessageDefinition() {
        return messageDefinitionContext.get();
    }

    public void clear() {
        messageHolderContext.remove();
        messageDefinitionContext.remove();
    }

    public static ExecutionContext instance() {
        return Inner.INSTANCE;
    }

    private static class Inner {
        private static final ExecutionContext INSTANCE = new ExecutionContext();
    }
}
