package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LongFieldBuilderTest {

    private LongFieldBuilder fieldBuilder = new LongFieldBuilder();

    @Test
    @DisplayName("order() == 21")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(21, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(Long.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        Long number = 16L;
        byte[] bytes = fieldBuilder.fromObjectToAscii(number, null);
        Assertions.assertEquals("3136", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        Long number = 16L;
        byte[] bytes = fieldBuilder.fromObjectToBinary(number, null);
        Assertions.assertEquals("10", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        Long number = 16L;
        byte[] bytes = fieldBuilder.fromObjectToBcd(number, null);
        Assertions.assertEquals("16", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        Long number = 16L;
        byte[] bytes = fieldBuilder.fromObjectToEbcdic(number, null);
        Assertions.assertEquals("F1F6", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        Long number = fieldBuilder.toObjectWithAscii(bytes, null);
        Assertions.assertEquals(16L, number);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        Long number = fieldBuilder.toObjectWithBinary(bytes, null);
        Assertions.assertEquals(16L, number);
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        Long number = fieldBuilder.toObjectWithBcd(bytes, null);
        Assertions.assertEquals(16L, number);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        Long number = fieldBuilder.toObjectWithEbcdic(bytes, null);
        Assertions.assertEquals(16L, number);
    }
}
