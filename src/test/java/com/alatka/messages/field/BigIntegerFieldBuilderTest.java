package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerFieldBuilderTest {

    private FieldBuilder fieldBuilder = new BigIntegerFieldBuilder();

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
        fieldDefinition.setOriginClass(BigInteger.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        BigInteger number = new BigInteger(String.valueOf(16));
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{BigInteger.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "3136");
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        BigInteger number = new BigInteger(String.valueOf(16));
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBinary",
                new Class[]{BigInteger.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "10");
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        BigInteger number = new BigInteger(String.valueOf(16));
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{BigInteger.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "16");
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        BigInteger number = new BigInteger(String.valueOf(16));
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToEbcdic",
                new Class[]{BigInteger.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "F1F6");
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        BigInteger number = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigInteger(String.valueOf(16)));
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        BigInteger number = ClassUtil.invoke(fieldBuilder, "toObjectWithBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigInteger(String.valueOf(16)));

    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        BigInteger number = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigInteger(String.valueOf(16)));
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        BigInteger number = ClassUtil.invoke(fieldBuilder, "toObjectWithEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigInteger(String.valueOf(16)));
    }
}
