package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.MessageHolderAware;
import com.alatka.messages.util.ClassUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ybliu
 */
public class DomainParsedFactory {

    private static final List<DomainParsed> LIST = new ArrayList<>();

    static {
        init(new UnfixedDomainParsed());
        init(new AsciiLVDomainParsed());
        init(new BinaryLVDomainParsed());
        init(new TLVDomainParsed());
        init(new TVDomainParsed());
        init(new FixedDomainParsed());
        init(new PageDomainParsed());
        init(new BitmapDomainParsed());
        init(new RawDomainParsed());
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

        DomainParsed result = (DomainParsed) ClassUtil.newInstance(domainParsed.getClass());
        result.setMessageDefinition(messageDefinition);
        if (result instanceof MessageHolderAware) {
            ((MessageHolderAware) result).setMessageHolder(instance);
        }
        return result;
    }
}
