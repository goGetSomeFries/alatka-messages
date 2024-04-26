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

    private FieldBuilder fieldBuilder = new StringFieldBuilder();

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
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{String.class, FieldDefinition.class}, new Object[]{str, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "3136");
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        String str = "10";
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBinary",
                new Class[]{String.class, FieldDefinition.class}, new Object[]{str, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "02");
    }

    @Test
    @DisplayName("fromObjectToBcd() fixed=true")
    void test05() {
        String str = "10";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(true);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{String.class, FieldDefinition.class}, new Object[]{str, definition});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "10");
    }

    @Test
    @DisplayName("fromObjectToBcd() fixed=false")
    void test06() {
        String str = "25";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(false);
        definition.setLength(2);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{String.class, FieldDefinition.class}, new Object[]{str, definition});
        Assertions.assertArrayEquals(bytes, new byte[]{0, 2, 37});
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test07() {
        String str = "12";
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToEbcdic",
                new Class[]{String.class, FieldDefinition.class}, new Object[]{str, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "F1F2");
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test08() {
        String actual = "16";
        String str = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual.getBytes(), null});
        Assertions.assertEquals(str, actual);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test09() {
        String actual = "00001111";
        String str = ClassUtil.invoke(fieldBuilder, "toObjectWithBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{BytesUtil.binaryToBytes(actual), null});
        Assertions.assertEquals(str, actual);
    }

    @Test
    @DisplayName("toObjectWithBcd() fixed=true")
    void test10() {
        String hex = "12";
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(true);
        String str = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{BytesUtil.hexToBytes(hex), definition});
        Assertions.assertEquals(str, "12");
    }

    @Test
    @DisplayName("toObjectWithBcd() fixed=false")
    void test11() {
        FieldDefinition definition = new FieldDefinition();
        definition.setFixed(false);
        definition.setLength(2);
        String str = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{new byte[]{0, 2, 18}, definition});
        Assertions.assertEquals(str, "12");
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test12() {
        String hex = "F1F2";
        String str = ClassUtil.invoke(fieldBuilder, "toObjectWithEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{BytesUtil.hexToBytes(hex), null});
        Assertions.assertEquals(str, "12");
    }
}
