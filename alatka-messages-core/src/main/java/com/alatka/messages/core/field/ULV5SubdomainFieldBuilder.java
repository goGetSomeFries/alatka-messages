package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

/**
 * ULV(usage-length-value)子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ULV5SubdomainFieldBuilder extends ULV2SubdomainFieldBuilder {


    @Override
    protected int usageLenLength() {
        return 3;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return definition.getExistSubdomain() && definition.getSubdomainType() == MessageDefinition.DomainType.ULV5;
    }
}
