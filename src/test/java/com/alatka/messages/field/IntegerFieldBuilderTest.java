package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerFieldBuilderTest {

    private FieldBuilder fieldBuilder = new IntegerFieldBuilder();

    @Test
    @DisplayName("order() == 22")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(22, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(Integer.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        Integer number = 16;
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{Integer.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "3136");
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        Integer number = 16;
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBinary",
                new Class[]{Integer.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "10");
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        Integer number = 16;
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{Integer.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "16");
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        Integer number = 16;
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToEbcdic",
                new Class[]{Integer.class, FieldDefinition.class}, new Object[]{number, null});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "F1F6");
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test07() {
        byte[] bytes = "16".getBytes();
        Integer number = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, 16);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test08() {
        byte[] bytes = BytesUtil.hexToBytes("10");
        Integer number = ClassUtil.invoke(fieldBuilder, "toObjectWithBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, 16);

    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test09() {
        byte[] bytes = BytesUtil.hexToBytes("16");
        Integer number = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, 16);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test10() {
        byte[] bytes = BytesUtil.hexToBytes("F1F6");
        Integer number = ClassUtil.invoke(fieldBuilder, "toObjectWithEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{bytes, null});
        Assertions.assertEquals(number, 16);
    }
}
