package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

/**
 * 子域类型报文域解析器
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
        return BytesUtil.toEBCDIC(id.getBytes());
    }

    @Override
    protected byte[] usageLen(int length) {
        return BytesUtil.toEBCDIC(String.valueOf(length).getBytes());
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 2;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.ULV_2;
    }
}
