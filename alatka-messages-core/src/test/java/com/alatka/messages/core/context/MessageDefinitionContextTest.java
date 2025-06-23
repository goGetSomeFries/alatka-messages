package com.alatka.messages.core.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MessageDefinitionContextTest {

    @Test
    @DisplayName("单例测试")
    public void test01() {
        MessageDefinitionContext instance1 = MessageDefinitionContext.getInstance();
        MessageDefinitionContext instance2 = MessageDefinitionContext.getInstance();
        Assertions.assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("messageDefinition(Class<?>)")
    void test02() {
        MessageDefinitionContext instance = MessageDefinitionContext.getInstance();
        Assertions.assertThrows(IllegalArgumentException.class, () -> instance.messageDefinition(Object.class), "class: 'Object' is not annotation by '@MessageMeta'");
    }

}
