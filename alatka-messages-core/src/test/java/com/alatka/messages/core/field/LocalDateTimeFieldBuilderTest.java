package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalDateTimeFieldBuilderTest {

    private LocalDateTimeFieldBuilder fieldBuilder = new LocalDateTimeFieldBuilder();

    @Test
    @DisplayName("order() == 82")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(82, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(LocalDateTime.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String time = "20240501213356";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").toFormatter();
        LocalDateTime result = fieldBuilder.parse(time, formatter);
        Assertions.assertEquals(LocalDateTime.parse(time, formatter), result);
    }

}
