package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BytesFieldBuilderTest {

    private BytesFieldBuilder fieldBuilder = new BytesFieldBuilder();

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
        fieldDefinition.setClassType(byte[].class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        byte[] expected = BytesUtil.hexToBytes("10");
        byte[] bytes = fieldBuilder.fromObjectToAscii(expected, null);
        Assertions.assertSame(bytes, expected);
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        byte[] expected = BytesUtil.hexToBytes("10");
        byte[] bytes = fieldBuilder.fromObjectToBinary(expected, null);
        Assertions.assertSame(bytes, expected);
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        byte[] expected = BytesUtil.hexToBytes("10");
        byte[] bytes = fieldBuilder.fromObjectToBcd(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        byte[] expected = BytesUtil.hexToBytes("10");
        byte[] bytes = fieldBuilder.fromObjectToEbcdic(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("fromObjectToNone()")
    void test07() {
        byte[] expected = BytesUtil.hexToBytes("10");
        byte[] bytes = fieldBuilder.fromObjectToNone(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test08() {
        byte[] expected = "16".getBytes();
        byte[] bytes = fieldBuilder.toObjectWithAscii(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test09() {
        byte[] expected = "16".getBytes();
        byte[] bytes = fieldBuilder.toObjectWithBinary(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test10() {
        byte[] expected = "16".getBytes();
        byte[] bytes = fieldBuilder.toObjectWithBcd(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test11() {
        byte[] expected = "16".getBytes();
        byte[] bytes = fieldBuilder.toObjectWithEbcdic(expected, null);
        Assertions.assertSame(expected, bytes);
    }

    @Test
    @DisplayName("toObjectWithNone()")
    void test12() {
        byte[] expected = "16".getBytes();
        byte[] bytes = fieldBuilder.toObjectWithNone(expected, null);
        Assertions.assertSame(expected, bytes);
    }
}
