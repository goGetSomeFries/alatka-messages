package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.MessageHolderAware;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * {@link FieldBuilder}工厂，维护{@link FieldBuilder}所有实例
 *
 * @author ybliu
 */
public class FieldBuilderFactory {

    private static final List<FieldBuilder> LIST = new ArrayList<>();

    static {
        // subdomain
        init(new FixedSubdomainFieldBuilder());
        init(new ULVSubdomainFieldBuilder());
        init(new ULV2SubdomainFieldBuilder());
        init(new UVSubdomainFieldBuilder());
        init(new UVASSubdomainFieldBuilder());
        init(new TLVSubdomainFieldBuilder());
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

    public static FieldBuilder getInstance(Object instance,
                                           MessageDefinition messageDefinition,
                                           FieldDefinition fieldDefinition) {
        FieldBuilder builder = LIST.stream()
                .filter(fieldBuilder -> fieldBuilder.matched(messageDefinition, fieldDefinition))
                .max(Comparator.comparing(FieldBuilder::getOrder))
                .orElseThrow(() -> new RuntimeException("FieldDefinition: " + fieldDefinition + " do not found matched 'FieldBuilder' instance"));

//        FieldBuilder result = (FieldBuilder) ClassUtil.newInstance(builder.getClass());
        builder.setMessageDefinition(messageDefinition);
        if (builder instanceof MessageHolderAware) {
            ((MessageHolderAware) builder).setMessageHolder(instance);
        }
        return builder;
    }
}
