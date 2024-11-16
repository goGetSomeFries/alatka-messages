package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.time.format.DateTimeFormatterBuilder;

public class YearMonthFieldBuilderTest {

    private YearMonthFieldBuilder fieldBuilder = new YearMonthFieldBuilder();

    @Test
    @DisplayName("order() == 85")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(85, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(YearMonth.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("parse()")
    void test03() {
        String yearMonth = "2024-03";
        YearMonth result = fieldBuilder.parse(yearMonth, new DateTimeFormatterBuilder().appendPattern("yyyy-MM").toFormatter());
        Assertions.assertEquals(YearMonth.parse(yearMonth), result);
    }

}
