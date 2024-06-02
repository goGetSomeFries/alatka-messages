package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IsoDatabaseMessageDefinitionBuilderTest {

    @Test
    @DisplayName("type()")
    void test01() {
        IsoDatabaseMessageDefinitionBuilder builder = new IsoDatabaseMessageDefinitionBuilder(null);
        Assertions.assertEquals(MessageDefinition.Type.iso, builder.type());
    }
}
