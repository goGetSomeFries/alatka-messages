package com.alatka.messages.definition;

import org.junit.jupiter.api.Test;

public class IsoAnnotationMessageDefinitionBuilderTest {

    @Test
    public void test() {
        MessageDefinitionBuilder builder = new IsoAnnotationMessageDefinitionBuilder("com.alatka.messages");
        builder.build();

    }

}
