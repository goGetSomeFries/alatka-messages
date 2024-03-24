package com.alatka.messages.definition;

import org.junit.jupiter.api.Test;

public class IsoYamlMessageDefinitionBuilderTest {

    @Test
    public void test() {
        MessageDefinitionBuilder builder = new IsoYamlMessageDefinitionBuilder();
        builder.build();
    }

}
