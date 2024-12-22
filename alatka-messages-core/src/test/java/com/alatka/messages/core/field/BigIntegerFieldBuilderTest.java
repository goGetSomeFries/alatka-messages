package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class BigIntegerFieldBuilderTest {

    private BigIntegerFieldBuilder fieldBuilder = new BigIntegerFieldBuilder();

    @Test
    @DisplayName("order() == 24")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(24, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setClassType(BigInteger.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        BigInteger number = new BigInteger("16");
        byte[] bytes = fieldBuilder.fromObjectToAscii(number, null);
        Assertions.assertEquals("3136", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        BigInteger number = new BigInteger("16");
        byte[] bytes = fieldBuilder.fromObjectToBinary(number, null);
        Assertions.assertEquals("10", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        BigInteger number = new BigInteger("16");
        byte[] bytes = fieldBuilder.fromObjectToBcd(number, null);
        Assertions.assertEquals("16", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        BigInteger number = new BigInteger("16");
        byte[] bytes = fieldBuilder.fromObjectToEbcdic(number, null);
        Assertions.assertEquals("F1F6", BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        BigInteger number = fieldBuilder.toObjectWithAscii(bytes, null);
        Assertions.assertEquals(new BigInteger("16"), number);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        BigInteger number = fieldBuilder.toObjectWithBinary(bytes, null);
        Assertions.assertEquals(new BigInteger("16"), number);

    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        BigInteger number = fieldBuilder.toObjectWithBcd(bytes, null);
        Assertions.assertEquals(new BigInteger("16"), number);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        BigInteger number = fieldBuilder.toObjectWithEbcdic(bytes, null);
        Assertions.assertEquals(new BigInteger("16"), number);
    }
}
