package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BooleanFieldBuilderTest {

    private BooleanFieldBuilder fieldBuilder = new BooleanFieldBuilder();

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
        fieldDefinition.setOriginClass(Boolean.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        byte[] bytes1 = fieldBuilder.fromObjectToAscii(true, null);
        Assertions.assertEquals("31", BytesUtil.bytesToHex(bytes1));
        byte[] bytes2 = fieldBuilder.fromObjectToAscii(false, null);
        Assertions.assertEquals("30", BytesUtil.bytesToHex(bytes2));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        byte[] bytes1 = fieldBuilder.fromObjectToBinary(true, null);
        Assertions.assertEquals("01", BytesUtil.bytesToHex(bytes1));
        byte[] bytes2 = fieldBuilder.fromObjectToBinary(false, null);
        Assertions.assertEquals("00", BytesUtil.bytesToHex(bytes2));
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        byte[] bytes1 = fieldBuilder.fromObjectToBcd(true, null);
        Assertions.assertEquals("01", BytesUtil.bytesToHex(bytes1));
        byte[] bytes2 = fieldBuilder.fromObjectToBcd(false, null);
        Assertions.assertEquals("00", BytesUtil.bytesToHex(bytes2));
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        byte[] bytes1 = fieldBuilder.fromObjectToEbcdic(true, null);
        Assertions.assertEquals("F1", BytesUtil.bytesToHex(bytes1));
        byte[] bytes2 = fieldBuilder.fromObjectToEbcdic(false, null);
        Assertions.assertEquals("F0", BytesUtil.bytesToHex(bytes2));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        Boolean value1 = fieldBuilder.toObjectWithAscii("1".getBytes(), null);
        Assertions.assertTrue(value1);
        Boolean value2 = fieldBuilder.toObjectWithAscii("0".getBytes(), null);
        Assertions.assertFalse(value2);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        Boolean value1 = fieldBuilder.toObjectWithBinary(BytesUtil.hexToBytes("01"), null);
        Assertions.assertTrue(value1);
        Boolean value2 = fieldBuilder.toObjectWithBinary(BytesUtil.hexToBytes("00"), null);
        Assertions.assertFalse(value2);
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        Boolean value1 = fieldBuilder.toObjectWithBcd(BytesUtil.hexToBytes("01"), null);
        Assertions.assertTrue(value1);
        Boolean value2 = fieldBuilder.toObjectWithBcd(BytesUtil.hexToBytes("00"), null);
        Assertions.assertFalse(value2);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        Boolean value1 = fieldBuilder.toObjectWithEbcdic(BytesUtil.hexToBytes("F1"), null);
        Assertions.assertTrue(value1);
        Boolean value2 = fieldBuilder.toObjectWithEbcdic(BytesUtil.hexToBytes("F0"), null);
        Assertions.assertFalse(value2);
    }

    @Test
    @DisplayName("convert(Boolean)")
    void test11() {
        int value1 = ClassUtil.invoke(fieldBuilder, "convert",
                new Class[]{Boolean.class}, new Object[]{true});
        Assertions.assertEquals(1, value1);
        int value2 = ClassUtil.invoke(fieldBuilder, "convert",
                new Class[]{Boolean.class}, new Object[]{false});
        Assertions.assertEquals(0, value2);
    }

    @Test
    @DisplayName("convert(Integer)")
    void test12() {
        Boolean value1 = ClassUtil.invoke(fieldBuilder, "convert",
                new Class[]{Integer.class}, new Object[]{1});
        Assertions.assertTrue(value1);
        Boolean value2 = ClassUtil.invoke(fieldBuilder, "convert",
                new Class[]{Integer.class}, new Object[]{0});
        Assertions.assertFalse(value2);
    }
}
