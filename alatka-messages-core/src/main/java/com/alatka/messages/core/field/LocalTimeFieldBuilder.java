package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalTime}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 * @see TimeFieldBuilder
 */
public class LocalTimeFieldBuilder extends TimeFieldBuilder<LocalTime> {

    @Override
    protected LocalTime parse(String datetime, DateTimeFormatter formatter) {
        return LocalTime.parse(datetime, formatter);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 81;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == LocalTime.class;
    }
}
