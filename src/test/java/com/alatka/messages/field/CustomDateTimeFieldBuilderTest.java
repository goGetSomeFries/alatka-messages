package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class CustomDateTimeFieldBuilderTest {

    private CustomDateTimeFieldBuilder fieldBuilder = new CustomDateTimeFieldBuilder();

    @Test
    @DisplayName("order() == 83")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(83, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(LocalDateTime.class);
        fieldDefinition.setPattern("MMddHHmmss");
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String time = "0501213356";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").toFormatter();
        LocalDateTime result = fieldBuilder.parse(time, formatter);
        Assertions.assertEquals(LocalDateTime.parse(LocalDate.now().getYear() + time, formatter), result);
    }

    @Test
    @DisplayName("doFormat() serialized=true")
    void test04() {
        String pattern = fieldBuilder.doFormat("yyyyMMdd", true);
        Assertions.assertEquals("yyyyMMdd", pattern);
    }

    @Test
    @DisplayName("doFormat() serialized=false")
    void test05() {
        String pattern = fieldBuilder.doFormat("MMdd", false);
        Assertions.assertEquals("uuuuMMdd", pattern);
    }
}
