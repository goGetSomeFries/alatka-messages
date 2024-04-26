package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RawFieldBuilderTest {

    private FieldBuilder fieldBuilder = new RawFieldBuilder();

    @Test
    @DisplayName("order() == 1000")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(1000, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setStatus(FieldDefinition.Status.RAW);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
        byte[] actual = BytesUtil.hexToBytes("10");
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("fromObjectToBinary()")
    void test04() {
        byte[] actual = BytesUtil.hexToBytes("10");
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test05() {
        byte[] actual = BytesUtil.hexToBytes("10");
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("fromObjectToEbcdic()")
    void test06() {
        byte[] actual = BytesUtil.hexToBytes("10");
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("fromObjectToNone()")
    void test07() {
        byte[] actual = BytesUtil.hexToBytes("10");
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToNone",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test08() {
        byte[] actual = "16".getBytes();
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test09() {
        byte[] actual = "16".getBytes();
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "toObjectWithBinary",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test10() {
        byte[] actual = "16".getBytes();
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("toObjectWithEbcdic()")
    void test11() {
        byte[] actual = "16".getBytes();
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "toObjectWithEbcdic",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }

    @Test
    @DisplayName("toObjectWithNone()")
    void test12() {
        byte[] actual = "16".getBytes();
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "toObjectWithNone",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual, null});
        Assertions.assertSame(bytes, actual);
    }
}
