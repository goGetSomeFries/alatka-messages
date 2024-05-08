package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AbstractFieldBuilderTest {

    @Spy
    private AbstractFieldBuilder fieldBuilder;

    @Test
    @DisplayName("order() == 0")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(0, order);
    }

    @Test
    @DisplayName("returnIfNull()")
    void test02() {
        Assertions.assertTrue(fieldBuilder.returnIfNull());
    }

    @Test
    @DisplayName("setMessageDefinition()")
    void test04() {
        MessageDefinition definition = new MessageDefinition();
        fieldBuilder.setMessageDefinition(definition);
        Assertions.assertSame(definition, fieldBuilder.messageDefinition);
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test05() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.fromObjectToAscii(null, definition));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test06() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.fromObjectToBinary(null, definition));
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test07() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.fromObjectToBcd(null, definition));
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test08() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.fromObjectToEbcdic(null, definition));
    }

    @Test
    @DisplayName("fromObjectToNone()")
    void test09() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.fromObjectToNone(null, definition));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test10() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.toObjectWithAscii(null, definition));
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test11() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.toObjectWithBinary(null, definition));
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test12() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.toObjectWithBcd(null, definition));
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test13() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.toObjectWithEbcdic(null, definition));
    }

    @Test
    @DisplayName("toObjectWithNone()")
    void test14() {
        FieldDefinition definition = new FieldDefinition();
        definition.setParseType(FieldDefinition.ParseType.ASCII);
        definition.setOriginClass(String.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fieldBuilder.toObjectWithNone(null, definition));
    }

    @Test
    @DisplayName("serialize()")
    void test15() {
        // TODO
    }

    @Test
    @DisplayName("deserialize()")
    void test16() {
        // TODO
    }
}
