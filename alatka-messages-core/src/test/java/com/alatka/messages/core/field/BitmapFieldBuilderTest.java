package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BitmapFieldBuilderTest {

    private BitmapFieldBuilder fieldBuilder = new BitmapFieldBuilder();

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setDomainNo(1);
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setKind(MessageDefinition.Kind.payload);
        Assertions.assertTrue(fieldBuilder.matched(messageDefinition, fieldDefinition));
    }

}
