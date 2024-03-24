package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalDateTime}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 * @see TimeFieldBuilder
 */
public class LocalDateTimeFieldBuilder extends TimeFieldBuilder<LocalDateTime> {

    @Override
    protected LocalDateTime parse(String datetime, DateTimeFormatter formatter) {
        return LocalDateTime.parse(datetime, formatter);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 82;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == LocalDateTime.class;
    }
}
