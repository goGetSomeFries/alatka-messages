package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * {@link MonthDay}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 * @see TimeFieldBuilder
 */
public class MonthDayFieldBuilder extends TimeFieldBuilder<MonthDay> {

    @Override
    protected MonthDay parse(String datetime, DateTimeFormatter formatter) {
        return MonthDay.parse(datetime, formatter);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 84;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == MonthDay.class;
    }
}
