package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.MessageHolderAware;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * {@link DomainParsed}工厂，维护{@link DomainParsed}所有实例
 *
 * @author ybliu
 */
public class DomainParsedFactory {

    private static final List<DomainParsed> LIST = new ArrayList<>();

    static {
        init(new RawDomainParsed());
        init(new BitmapDomainParsed());
        init(new TVDomainParsed());
        init(new TLVDomainParsed());
        init(new TLV2DomainParsed());
        init(new UnfixedDomainParsed());
        init(new BinaryLVDomainParsed());
        init(new AsciiLVDomainParsed());
        init(new EbcdicLVDomainParsed());
        init(new PageDomainParsed());
        init(new FixedDomainParsed());
    }

    public static void init(DomainParsed domainParsed) {
        LIST.add(domainParsed);
    }

    public static DomainParsed getInstance(Object instance,
                                           MessageDefinition messageDefinition,
                                           FieldDefinition fieldDefinition) {
        DomainParsed domainParsed = LIST.stream()
                .filter(parsed -> parsed.matched(messageDefinition, fieldDefinition))
                .max(Comparator.comparingInt(DomainParsed::getOrder))
                .orElseThrow(() -> new RuntimeException("do not found matched 'DomainParsed' instance"));

        if (domainParsed instanceof MessageHolderAware) {
            ((MessageHolderAware) domainParsed).setMessageHolder(instance);
        }
        return domainParsed;
    }
}
