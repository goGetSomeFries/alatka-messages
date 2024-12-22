package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFieldBuilderTest {

    private DateFieldBuilder fieldBuilder = new DateFieldBuilder();

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
        fieldDefinition.setClassType(Date.class);
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
        byte[] bytes = fieldBuilder.fromObjectToAscii(date, fieldDefinition);
        Assertions.assertEquals(str, new String(bytes));
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
        byte[] bytes = fieldBuilder.fromObjectToBcd(date, fieldDefinition);
        Assertions.assertEquals(str, BytesUtil.bytesToHex(bytes));
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test05() {
        String expected = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        Date date = fieldBuilder.toObjectWithAscii(expected.getBytes(), fieldDefinition);
        Assertions.assertEquals(expected, format.format(date));
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test06() {
        String expected = "20240506";
        String pattern = "yyyyMMdd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setPattern(pattern);
        Date date = fieldBuilder.toObjectWithBcd(BytesUtil.hexToBytes(expected), fieldDefinition);
        Assertions.assertEquals(expected, format.format(date));
    }

    @Test
    @DisplayName("getDate()")
    void test07() {
        String pattern = "yyyyMMdd";
        FieldDefinition definition = new FieldDefinition();
        definition.setPattern(pattern);
        String str = "20240502";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = ClassUtil.invoke(fieldBuilder, "getDate",
                new Class[]{FieldDefinition.class, String.class}, new Object[]{definition, str});
        Assertions.assertEquals(str, format.format(date));
    }

    @Test
    @DisplayName("getDate() exception")
    void test08() {
        String pattern = "yyyyMMdd";
        FieldDefinition definition = new FieldDefinition();
        definition.setPattern(pattern);
        String str = "Abdc";
        Assertions.assertThrows(RuntimeException.class,
                () -> ClassUtil.invoke(fieldBuilder, "getDate",
                        new Class[]{FieldDefinition.class, String.class}, new Object[]{definition, str}));
    }
}
