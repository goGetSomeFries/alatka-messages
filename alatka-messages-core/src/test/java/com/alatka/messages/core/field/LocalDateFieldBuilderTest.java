package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalDateFieldBuilderTest {

    private LocalDateFieldBuilder fieldBuilder = new LocalDateFieldBuilder();

    @Test
    @DisplayName("order() == 80")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(80, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setClassType(LocalDate.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String time = "20240501";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyyMMdd").toFormatter();
        LocalDate result = fieldBuilder.parse(time, formatter);
        Assertions.assertEquals(LocalDate.parse(time, formatter), result);
    }

}
