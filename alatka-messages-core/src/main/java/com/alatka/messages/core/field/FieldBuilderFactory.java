package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {@link FieldBuilder}工厂，维护{@link FieldBuilder}所有实例
 *
 * @author ybliu
 */
public class FieldBuilderFactory {

    private static final List<FieldBuilder> LIST = new CopyOnWriteArrayList<>();

    static {
        // subdomain
        init(new FixedSubdomainFieldBuilder());
        init(new ULVSubdomainFieldBuilder());
        init(new ULV2SubdomainFieldBuilder());
        init(new ULV3SubdomainFieldBuilder());
        init(new ULV4SubdomainFieldBuilder());
        init(new ULV5SubdomainFieldBuilder());
        init(new UVSubdomainFieldBuilder());
        init(new UVASSubdomainFieldBuilder());
        init(new TLVSubdomainFieldBuilder());
        init(new TLV2SubdomainFieldBuilder());
        init(new BitmapSubdomainFieldBuilder());
        init(new PageSubdomainFieldBuilder());
        // datetime
        init(new LocalDateTimeFieldBuilder());
        init(new CustomDateTimeFieldBuilder());
        init(new LocalDateFieldBuilder());
        init(new LocalTimeFieldBuilder());
        init(new DateFieldBuilder());
        init(new YearMonthFieldBuilder());
        init(new MonthDayFieldBuilder());
        // byte[]
        init(new BytesFieldBuilder());
        // string
        init(new StringFieldBuilder());
        // number
        init(new IntegerFieldBuilder());
        init(new BigDecimalFieldBuilder());
        init(new BigIntegerFieldBuilder());
        init(new LongFieldBuilder());
        // boolean
        init(new BooleanFieldBuilder());
        // bitmap
        init(new BitmapFieldBuilder());
        init(new SubdomainBitmapFieldBuilder());
        // raw
        init(new RawFieldBuilder());
    }

    public static void init(FieldBuilder fieldBuilder) {
        LIST.add(fieldBuilder);
    }

    public static FieldBuilder getInstance(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return LIST.stream()
                .filter(fieldBuilder -> fieldBuilder.matched(messageDefinition, fieldDefinition))
                .max(Comparator.comparing(FieldBuilder::getOrder))
                .orElseThrow(() -> new RuntimeException("FieldDefinition: " + fieldDefinition + " do not found matched 'FieldBuilder' instance"));
    }
}
