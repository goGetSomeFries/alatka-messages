package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link Date}类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class DateFieldBuilder extends AbstractFieldBuilder<Date> {

    protected SimpleDateFormat formatter(FieldDefinition fieldDefinition) {
        String pattern = fieldDefinition.getPattern();
        return new SimpleDateFormat(pattern);
    }

    @Override
    protected byte[] fromObjectToAscii(Date value, FieldDefinition fieldDefinition) {
        String datetime = this.formatter(fieldDefinition).format(value);
        return datetime.getBytes();
    }

    @Override
    protected byte[] fromObjectToBcd(Date value, FieldDefinition fieldDefinition) {
        String datetime = this.formatter(fieldDefinition).format(value);
        return BytesUtil.toBCD(datetime);
    }

    @Override
    protected Date toObjectWithAscii(byte[] bytes, FieldDefinition fieldDefinition) {
        String datetime = new String(bytes);
        return getDate(fieldDefinition, datetime);
    }

    @Override
    protected Date toObjectWithBcd(byte[] bytes, FieldDefinition fieldDefinition) {
        String datetime = BytesUtil.fromBCD(bytes);
        return getDate(fieldDefinition, datetime);
    }

    private Date getDate(FieldDefinition fieldDefinition, String datetime) {
        try {
            return this.formatter(fieldDefinition).parse(datetime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("fieldDefinition: " + fieldDefinition, e);
        }
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 90;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getClassType() == Date.class;
    }
}
