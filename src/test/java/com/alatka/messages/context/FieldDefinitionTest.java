package com.alatka.messages.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
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
        Assertions.assertTrue(definition1.equals(definition1));
        Assertions.assertFalse(definition1.equals(null));
        Assertions.assertFalse(definition1.equals("123"));
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
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setIndex(3);
        FieldDefinition definition2 = new FieldDefinition();
        definition2.setIndex(1);
        int result = definition1.compareTo(definition2);
        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("compareTo()")
    void test04() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setIndex(3);
        FieldDefinition definition2 = new FieldDefinition();
        definition2.setIndex(1);
        int result = definition1.compareTo(definition2);
        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("getter() setter()")
    void test05() {
        // TODO
    }

    @Test
    @DisplayName("MessageDefinition.Status")
    void test06() {
        FieldDefinition.Status[] values = FieldDefinition.Status.values();
        String[] array = {"OPEN", "CLOSE", "RAW"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

    @Test
    @DisplayName("MessageDefinition.ParseType")
    void test07() {
        FieldDefinition.ParseType[] values = FieldDefinition.ParseType.values();
        String[] array = {"ASCII", "EBCDIC", "BCD", "BINARY", "NONE", "NONE_V2"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

    @Test
    @DisplayName("MessageDefinition.ParseType.LPT")
    void test08() {
        FieldDefinition.ParseType.LPT[] values = FieldDefinition.ParseType.LPT.values();
        String[] array = {"A", "B"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }
}
