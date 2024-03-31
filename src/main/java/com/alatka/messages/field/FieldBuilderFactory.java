package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.MessageHolderAware;
import com.alatka.messages.util.ClassUtil;

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
        init(new DefaultSubdomainFieldBuilder<>());
        init(new ULVSubdomainFieldBuilder());
        init(new ULV2SubdomainFieldBuilder());
        init(new UVSubdomainFieldBuilder());
        init(new UVASSubdomainFieldBuilder());
        init(new TLVSubdomainFieldBuilder());
        // collection
        init(new ListFieldBuilder());
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
        init(new LongFieldBuilder());
        // map
        init(new BitmapFieldBuilder());
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

        FieldBuilder result = (FieldBuilder) ClassUtil.newInstance(builder.getClass());
        result.setMessageDefinition(messageDefinition);
        if (result instanceof MessageHolderAware) {
            ((MessageHolderAware) result).setMessageHolder(instance);
        }
        return result;
    }
}
