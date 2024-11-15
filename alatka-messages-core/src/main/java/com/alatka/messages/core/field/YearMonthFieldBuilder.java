package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * {@link YearMonth}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 * @see TimeFieldBuilder
 */
public class YearMonthFieldBuilder extends TimeFieldBuilder<YearMonth> {

    @Override
    protected YearMonth parse(String datetime, DateTimeFormatter formatter) {
        return YearMonth.parse(datetime, formatter);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 85;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClazz() == YearMonth.class;
    }
}
