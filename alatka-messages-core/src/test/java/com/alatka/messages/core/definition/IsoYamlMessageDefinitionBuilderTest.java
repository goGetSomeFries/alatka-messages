package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IsoYamlMessageDefinitionBuilderTest {

    private IsoYamlMessageDefinitionBuilder builder = new IsoYamlMessageDefinitionBuilder();

    @Test
    @DisplayName("type()")
    void test01() {
        Assertions.assertEquals(MessageDefinition.Type.iso, builder.type());
    }

    @Test
    @DisplayName("fieldDefinitionClass()")
    void test02() {
        Assertions.assertEquals(IsoFieldDefinition.class, builder.fieldDefinitionClass());
    }

    @Test
    @DisplayName("postBuildFieldDefinition()")
    void test03() {
        IsoFieldDefinition definition = new IsoFieldDefinition();
        definition.setFixed(true);
        builder.postBuildFieldDefinition(null, definition);
        Assertions.assertEquals(0, definition.getLength());
        Assertions.assertEquals(0, definition.getMaxLength());
    }
}
