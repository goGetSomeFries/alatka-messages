package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberFieldBuilderTest {

    private NumberFieldBuilder fieldBuilder = new NumberFieldBuilder() {
        @Override
        public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
            return false;
        }
    };

    @Test
    @DisplayName("order() == 20")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(20, order);
    }
}
