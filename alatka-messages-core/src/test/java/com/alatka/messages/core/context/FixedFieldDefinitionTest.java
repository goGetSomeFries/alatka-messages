package com.alatka.messages.core.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Objects;

public class FixedFieldDefinitionTest {

    @Test
    @DisplayName("toString()")
    void test01() {
        FixedFieldDefinition definition = new FixedFieldDefinition ();
        definition.setDomainNo(1);
        definition.setName("test");
        definition.setLength(2);
        definition.setRemark("testing");

        Assertions.assertEquals("{F1:test:2:testing}", definition.toString());
    }

}
