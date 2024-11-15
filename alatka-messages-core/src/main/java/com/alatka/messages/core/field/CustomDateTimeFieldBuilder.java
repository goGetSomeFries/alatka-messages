package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalDateTime}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 * @see LocalDateTimeFieldBuilder
 */
public class CustomDateTimeFieldBuilder extends LocalDateTimeFieldBuilder {

    @Override
    protected LocalDateTime parse(String datetime, DateTimeFormatter formatter) {
        return LocalDateTime.parse(LocalDate.now().getYear() + datetime, formatter);
    }

    @Override
    protected String doFormat(String pattern, boolean serialized) {
        return serialized ? pattern : "uuuu".concat(pattern);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 1;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        String pattern = definition.getPattern();
        return super.matched(messageDefinition, definition) && !pattern.matches("^[y|u]{1,4}\\\\w+");
    }
}
