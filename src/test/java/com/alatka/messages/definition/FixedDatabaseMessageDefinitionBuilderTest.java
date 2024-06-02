package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixedDatabaseMessageDefinitionBuilderTest {

    @Test
    @DisplayName("type()")
    void test01() {
        FixedDatabaseMessageDefinitionBuilder builder = new FixedDatabaseMessageDefinitionBuilder(null);
        Assertions.assertEquals(MessageDefinition.Type.fixed, builder.type());
    }
}
