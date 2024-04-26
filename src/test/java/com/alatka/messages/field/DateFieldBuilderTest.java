package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFieldBuilderTest {

    private FieldBuilder fieldBuilder = new DateFieldBuilder();

    @Test
    @DisplayName("order() == 90")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(90, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(Date.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() throws ParseException {
        String str = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(str);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToAscii",
                new Class[]{Date.class, FieldDefinition.class}, new Object[]{date, fieldDefinition});
        Assertions.assertEquals(new String(bytes), str);
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test04() throws ParseException {
        String str = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(str);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        byte[] bytes = ClassUtil.invoke(fieldBuilder, "fromObjectToBcd",
                new Class[]{Date.class, FieldDefinition.class}, new Object[]{date, fieldDefinition});
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), str);
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test05() {
        String actual = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        Date date = ClassUtil.invoke(fieldBuilder, "toObjectWithAscii",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{actual.getBytes(), fieldDefinition});
        Assertions.assertEquals(format.format(date), actual);
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test06() {
        String actual = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        Date date = ClassUtil.invoke(fieldBuilder, "toObjectWithBcd",
                new Class[]{byte[].class, FieldDefinition.class}, new Object[]{BytesUtil.hexToBytes(actual), fieldDefinition});
        Assertions.assertEquals(format.format(date), actual);
    }

}
