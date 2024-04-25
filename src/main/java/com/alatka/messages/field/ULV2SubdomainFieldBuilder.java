package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

/**
 * ULV(usage-length-value)子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ULV2SubdomainFieldBuilder extends AbstractULVSubdomainFieldBuilder {


    @Override
    protected int usageIdLength() {
        return 2;
    }

    @Override
    protected int usageLenLength() {
        return 2;
    }

    @Override
    protected String usageId(byte[] bytes) {
        return BytesUtil.fromEBCDIC(bytes);
    }

    @Override
    protected int usageLen(byte[] bytes) {
        return Integer.parseInt(BytesUtil.fromEBCDIC(bytes));
    }

    @Override
    protected byte[] usageId(String id) {
        return BytesUtil.toEBCDIC(id);
    }

    @Override
    protected byte[] usageLen(int length) {
        String str = String.format("%0" + this.usageLenLength() + "d", length);
        return BytesUtil.toEBCDIC(str);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.ULV_V2;
    }
}
