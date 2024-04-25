package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalFieldBuilderTest {

    private FieldBuilder fieldBuilder = new BigDecimalFieldBuilder();

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
        fieldDefinition.setOriginClass(BigDecimal.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{BigDecimal.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "3136");
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBinary",
                new Class[]{BigDecimal.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "10");
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{BigDecimal.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "16");
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        BigDecimal number = new BigDecimal(16);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToEbcdic",
                new Class[]{BigDecimal.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "F1F6");
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        BigDecimal number = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigDecimal(16));
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        BigDecimal number = ClassUtil.invoke(fieldBuilder, "toObjectWithBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigDecimal(16));

    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        BigDecimal number = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigDecimal(16));
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        BigDecimal number = ClassUtil.invoke(fieldBuilder, "toObjectWithEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, new BigDecimal(16));
    }
}
