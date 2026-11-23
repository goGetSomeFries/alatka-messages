package com.alatka.messages.core.support;

import com.alatka.messages.core.context.MessageDefinition;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 报文解析执行上下文
 */
public class ExecutionContext {

    // 递归调用场景下使用 Deque
    private final ThreadLocal<Deque<Wrapper>> stackContext = ThreadLocal.withInitial(ArrayDeque::new);

    private ExecutionContext() {
    }

    public void set(Object messageHolder, MessageDefinition messageDefinition) {
        stackContext.get().push(new Wrapper(messageHolder, messageDefinition));
    }

    public void clear() {
        Deque<Wrapper> stack = stackContext.get();
        if (!stack.isEmpty()) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            stackContext.remove();
        }
    }

    public Object getMessageHolder() {
        Wrapper top = this.peek();
        return top == null ? null : top.messageHolder;
    }

    public MessageDefinition getMessageDefinition() {
        Wrapper top = this.peek();
        return top == null ? null : top.messageDefinition;
    }

    private Wrapper peek() {
        Deque<Wrapper> stack = stackContext.get();
        return stack.isEmpty() ? null : stack.peek();
    }

    private static class Wrapper {

        private final Object messageHolder;
        private final MessageDefinition messageDefinition;

        private Wrapper(Object messageHolder, MessageDefinition messageDefinition) {
            this.messageHolder = messageHolder;
            this.messageDefinition = messageDefinition;
        }
    }


    public static ExecutionContext instance() {
        return Inner.INSTANCE;
    }

    private static class Inner {
        private static final ExecutionContext INSTANCE = new ExecutionContext();
    }
}
