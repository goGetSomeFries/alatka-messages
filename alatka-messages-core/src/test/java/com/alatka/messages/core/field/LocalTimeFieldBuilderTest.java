package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalTimeFieldBuilderTest {

    private LocalTimeFieldBuilder fieldBuilder = new LocalTimeFieldBuilder();

    @Test
    @DisplayName("order() == 81")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(81, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(LocalTime.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String time = "235830";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("HHmmss").toFormatter();
        LocalTime result = fieldBuilder.parse(time, formatter);
        Assertions.assertEquals(LocalTime.parse(time, formatter), result);
    }

}
