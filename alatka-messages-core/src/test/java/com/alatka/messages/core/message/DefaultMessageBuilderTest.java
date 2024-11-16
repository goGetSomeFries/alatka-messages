package com.alatka.messages.core.message;

import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new DefaultMessageBuilder(definition));
    }

}
