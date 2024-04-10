package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * ULV(usage-length-value)子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ULVSubdomainFieldBuilder extends AbstractULVSubdomainFieldBuilder {


    @Override
    protected int usageIdLength() {
        return 2;
    }

    @Override
    protected int usageLenLength() {
        return 3;
    }

    @Override
    protected String usageId(byte[] bytes) {
        return new String(bytes);
    }

    @Override
    protected int usageLen(byte[] bytes) {
        return Integer.parseInt(new String(bytes));
    }

    @Override
    protected byte[] usageId(String id) {
        return id.getBytes();
    }

    @Override
    protected byte[] usageLen(int length) {
        return String.format("%0" + this.usageLenLength() + "d", length).getBytes();
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.ULV;
    }
}
