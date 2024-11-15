package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.FixedFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixedXmlMessageDefinitionBuilderTest {

    @Test
    @DisplayName("type()")
    void test01() {
        FixedXmlMessageDefinitionBuilder builder = new FixedXmlMessageDefinitionBuilder();
        Assertions.assertEquals(MessageDefinition.Type.fixed, builder.type());
    }

    @Test
    @DisplayName("fieldDefinitionClass()")
    void test02() {
        FixedXmlMessageDefinitionBuilder builder = new FixedXmlMessageDefinitionBuilder();
        Assertions.assertEquals(FixedFieldDefinition.class, builder.fieldDefinitionClass());
    }

    @Test
    @DisplayName("postBuildFieldDefinition()")
    void test03() {
        FixedXmlMessageDefinitionBuilder builder = new FixedXmlMessageDefinitionBuilder();

        MessageDefinition messageDefinition = null;
        FieldDefinition fieldDefinition = null;
        builder.postBuildFieldDefinition(messageDefinition, fieldDefinition);

        Assertions.assertNull(messageDefinition);
        Assertions.assertNull(fieldDefinition);
    }
}
