package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

/**
 * 时间类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public abstract class TimeFieldBuilder<T> extends AbstractFieldBuilder<T> {

    /**
     * 字符串转日期类型
     *
     * @param datetime 日期类型字符串
     * @param formatter {@link DateTimeFormatter}
     * @return 日期类型对象
     */
    protected abstract T parse(String datetime, DateTimeFormatter formatter);

    @Override
    protected T toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        String datetime = new String(bytes);
        if (validate(datetime)) {
            return null;
        }
        return this.parse(datetime, this.formatter(fieldDefinition.getPattern(), false));
    }

    @Override
    protected T toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        String datetime = BytesUtil.fromBCD(bytes);
        if (validate(datetime)) {
            return null;
        }
        return this.parse(datetime, this.formatter(fieldDefinition.getPattern(), false));
    }

    @Override
    protected byte[] fromObjectToAscii(T value, FieldDefinition fieldDefinition) {
        String datetime = this.formatter(fieldDefinition.getPattern(), true).format((TemporalAccessor) value);
        return datetime.getBytes();
    }

    @Override
    protected byte[] fromObjectToBcd(T value, FieldDefinition fieldDefinition) {
        String datetime = this.formatter(fieldDefinition.getPattern(), true).format((TemporalAccessor) value);
        return BytesUtil.toBCD(datetime);
    }

    private DateTimeFormatter formatter(String pattern, boolean serialized) {
        pattern = this.doFormat(pattern, serialized);
        return "yyyyMMddHHmmssSSS".equals(pattern) ?
                new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter() :
                DateTimeFormatter.ofPattern(pattern);
    }

    protected String doFormat(String pattern, boolean serialized) {
        return pattern;
    }

    private boolean validate(String datetime) {
        return datetime.matches("^0+$");
    }
}
