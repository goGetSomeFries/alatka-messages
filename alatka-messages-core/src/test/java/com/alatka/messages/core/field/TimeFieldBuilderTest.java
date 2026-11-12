package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@ExtendWith(MockitoExtension.class)
public class TimeFieldBuilderTest {

    private LocalDateFieldBuilder fieldBuilder = new LocalDateFieldBuilder();

    @Test
    @DisplayName("normalizeType()")
    void test01() {
        String date = "20240501";
        FieldDefinition definition = new FieldDefinition();
        String pattern = "yyyyMMdd";
        definition.setPattern(pattern);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();
        LocalDate result = fieldBuilder.normalizeType(date, definition);
        Assertions.assertEquals(LocalDate.parse(date, formatter), result);
    }

    @Test
    @DisplayName("doDenormalizeType()")
    void test02() {
        String date = "20240501";
        FieldDefinition definition = new FieldDefinition();
        String pattern = "yyyyMMdd";
        definition.setPattern(pattern);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();

        fieldBuilder.doDenormalizeType(LocalDate.parse(date, formatter), definition);
    }
}
