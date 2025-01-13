package com.alatka.messages.core.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IsoFieldDefinitionTest {

    @Test
    @DisplayName("toString()")
    void test01() {
        IsoFieldDefinition definition = new IsoFieldDefinition();
        definition.setDomainNo(1);
        definition.setAliasName("test2");
        definition.setName("test");
        definition.setFixed(true);
        definition.setLength(2);
        definition.setRemark("testing");

        Assertions.assertEquals("{F1:test2:test:2:testing}", definition.toString());
    }

    @Test
    @DisplayName("toString()")
    void test02() {
        IsoFieldDefinition definition = new IsoFieldDefinition();
        definition.setDomainNo(1);
        definition.setAliasName("test2");
        definition.setName("test");
        definition.setFixed(false);
        definition.setLength(2);
        definition.setMaxLength(5);
        definition.setRemark("testing");

        Assertions.assertEquals("{F1:test2:test:2~5:testing}", definition.toString());
    }

    @Test
    @DisplayName("toString()")
    void test03() {
        IsoFieldDefinition definition = new IsoFieldDefinition();
        definition.setDomainNo(1);
        definition.setAliasName(null);
        definition.setName("test");
        definition.setFixed(false);
        definition.setLength(2);
        definition.setMaxLength(5);
        definition.setRemark("testing");

        Assertions.assertEquals("{F1:test:2~5:testing}", definition.toString());
    }

    @Test
    @DisplayName("getter() setter()")
    void test04() {
        IsoFieldDefinition definition = new IsoFieldDefinition();
        definition.setLenParseType(FieldDefinition.ParseType.ASCII);
        definition.setAliasName("alias");
        definition.setMaxLength(20);
        definition.setNonSubdomainException(false);

        Assertions.assertEquals(FieldDefinition.ParseType.ASCII, definition.getLenParseType());
        Assertions.assertEquals("alias", definition.getAliasName());
        Assertions.assertEquals(20, definition.getMaxLength());
        Assertions.assertEquals(false, definition.getNonSubdomainException());
    }
}
