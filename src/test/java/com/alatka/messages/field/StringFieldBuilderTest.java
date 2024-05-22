package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringFieldBuilderTest {

    private StringFieldBuilder fieldBuilder = new StringFieldBuilder();

    @BeforeEach
    void init() {
        MessageDefinition definition = new MessageDefinition();
        definition.setCharset("GBK");
        fieldBuilder.setMessageDefinition(definition);
    }

    @Test
    @DisplayName("order() == 0")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(0, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(String.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        String str = "16";
        byte[] bytes = fieldBuilder.fromObjectToAscii(str, null);
        Assertions.assertEquals("3136", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        String str = "10";
        byte[] bytes = fieldBuilder.fromObjectToBinary(str, null);
        Assertions.assertEquals("02", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBcd() fixed=true")
    void test05() {
        String str = "10";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(true);
        byte[] bytes = fieldBuilder.fromObjectToBcd(str, definition);
        Assertions.assertEquals("10", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBcd() fixed=false")
    void test06() {
        String str = "25";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(false);
        definition.setLength(2);
        byte[] bytes = fieldBuilder.fromObjectToBcd(str, definition);
        Assertions.assertArrayEquals(new byte[]{0, 2, 37}, bytes);
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test07() {
        String str = "12";
        byte[] bytes = fieldBuilder.fromObjectToEbcdic(str, null);
        Assertions.assertEquals("F1F2", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test08() {
        String expected = "16";
        String str = fieldBuilder.toObjectWithAscii(expected.getBytes(), null);
        Assertions.assertEquals(expected, str);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test09() {
        String expected = "00001111";
        String str = fieldBuilder.toObjectWithBinary(BytesUtil.binaryToBytes(expected), null);
        Assertions.assertEquals(expected, str);
    }

    @Test
    @DisplayName("toObjectWithBcd() fixed=true")
    void test10() {
        String hex = "12";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(true);
        String str = fieldBuilder.toObjectWithBcd(BytesUtil.hexToBytes(hex), definition);
        Assertions.assertEquals("12", str);
    }

    @Test
    @DisplayName("toObjectWithBcd() fixed=false")
    void test11() {
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(false);
        definition.setLength(2);
        String str = fieldBuilder.toObjectWithBcd(new byte[]{0, 2, 18}, definition);
        Assertions.assertEquals("12", str);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test12() {
        String hex = "F1F2";
        String str = fieldBuilder.toObjectWithEbcdic(BytesUtil.hexToBytes(hex), null);
        Assertions.assertEquals("12", str);
    }

    @Test
    @DisplayName("postProcess()")
    void test13() {
        String str = "  123 ";
        String result = ClassUtil.invoke(fieldBuilder, "postProcess",
                new Class[]{String.class}, new Object[]{str});
        Assertions.assertEquals("123", result);
    }

    @Test
    @DisplayName("postProcess()")
    void test14() {
        String str = "   ";
        String result = ClassUtil.invoke(fieldBuilder, "postProcess",
                new Class[]{String.class}, new Object[]{str});
        Assertions.assertNull(result);
    }
}
