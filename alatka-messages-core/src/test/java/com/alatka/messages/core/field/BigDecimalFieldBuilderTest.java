package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalFieldBuilderTest {

    private BigDecimalFieldBuilder fieldBuilder = new BigDecimalFieldBuilder();

    @Test
    @DisplayName("order() == 23")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(23, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setClassType(BigDecimal.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = fieldBuilder.fromObjectToAscii(number, new FieldDefinition());
        Assertions.assertEquals("3136", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = fieldBuilder.fromObjectToBinary(number, new FieldDefinition());
        Assertions.assertEquals("10", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = fieldBuilder.fromObjectToBcd(number, new FieldDefinition());
        Assertions.assertEquals("16", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = fieldBuilder.fromObjectToEbcdic(number, new FieldDefinition());
        Assertions.assertEquals("F1F6", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        BigDecimal number = fieldBuilder.toObjectWithAscii(bytes, new FieldDefinition());
        Assertions.assertEquals(new BigDecimal("16"), number);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        BigDecimal number = fieldBuilder.toObjectWithBinary(bytes, new FieldDefinition());
        Assertions.assertEquals(new BigDecimal("16"), number);

    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        BigDecimal number = fieldBuilder.toObjectWithBcd(bytes, new FieldDefinition());
        Assertions.assertEquals(new BigDecimal("16"), number);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        BigDecimal number = fieldBuilder.toObjectWithEbcdic(bytes, new FieldDefinition());
        Assertions.assertEquals(new BigDecimal("16"), number);
    }

    @Test
    @DisplayName("toConvert()")
    void test11() {
        byte[] bytes = "1624".getBytes();
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern("P2");
        BigDecimal number = fieldBuilder.toObjectWithAscii(bytes, fieldDefinition);
        Assertions.assertEquals(new BigDecimal("16.24"), number);
    }

    @Test
    @DisplayName("toConvert()")
    void test12() {
        byte[] bytes = "16241".getBytes();
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern("P3S2");
        BigDecimal number = fieldBuilder.toObjectWithAscii(bytes, fieldDefinition);
        Assertions.assertEquals(new BigDecimal("16.24"), number);
    }

    @Test
    @DisplayName("fromConvert()")
    void test13() {
        BigDecimal number = new BigDecimal("16.241");
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern("P3S2");
        byte[] bytes = fieldBuilder.fromObjectToAscii(number, fieldDefinition);
        Assertions.assertEquals("3136323430", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromConvert()")
    void test14() {
        BigDecimal number = new BigDecimal("16.24");
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern("P2");
        byte[] bytes = fieldBuilder.fromObjectToAscii(number, fieldDefinition);
        Assertions.assertEquals("31363234", BytesUtil.bytesToHex(bytes));
    }
}
