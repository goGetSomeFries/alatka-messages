package com.alatka.messages.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class FieldDefinitionTest {

    @Test
    @DisplayName("equals()")
    void test01() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setIndex(1);
        definition1.setDomainNo(1);
        definition1.setName("test");
        FieldDefinition definition2 = new FieldDefinition();
        definition2.setIndex(1);
        definition2.setDomainNo(1);
        definition2.setName("test");
        Assertions.assertTrue(definition1.equals(definition2));
    }

    @Test
    @DisplayName("hashcode()")
    void test02() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setIndex(1);
        definition1.setDomainNo(1);
        definition1.setName("test");

        int actual = Objects.hash(definition1.getIndex(), definition1.getDomainNo(), definition1.getName());
        Assertions.assertEquals(definition1.hashCode(), actual);
    }


    @Test
    @DisplayName("compareTo()")
    void test03() {
        // TODO
    }
}
