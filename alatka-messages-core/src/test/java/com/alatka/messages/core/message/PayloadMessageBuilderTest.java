package com.alatka.messages.core.message;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PayloadMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new PayloadMessageBuilder(definition));
    }

    @Test
    @DisplayName("filter() domain=1")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        PayloadMessageBuilder messageBuilder = new PayloadMessageBuilder(definition);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setDomainNo(1);
        Assertions.assertTrue(messageBuilder.filter(fieldDefinition));
    }

    @Test
    @DisplayName("filter() domain>1")
    void test03() {
        // TODO
    }
}
