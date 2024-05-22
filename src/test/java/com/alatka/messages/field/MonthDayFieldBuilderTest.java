package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.format.DateTimeFormatterBuilder;

public class MonthDayFieldBuilderTest {

    private MonthDayFieldBuilder fieldBuilder = new MonthDayFieldBuilder();

    @Test
    @DisplayName("order() == 84")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(84, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(MonthDay.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String monthDay = "--03-01";
        MonthDay result = fieldBuilder.parse(monthDay, new DateTimeFormatterBuilder().appendPattern("--MM-dd").toFormatter());
        Assertions.assertEquals(MonthDay.parse(monthDay), result);
    }

}
