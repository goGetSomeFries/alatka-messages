package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * ULV(usage-length-value)子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ULV3SubdomainFieldBuilder extends AbstractULVSubdomainFieldBuilder {


    @Override
    protected int usageIdLength() {
        return 1;
    }

    @Override
    protected int usageLenLength() {
        return 2;
    }

    @Override
    protected String usageId(byte[] bytes) {
        return BytesUtil.bytesToHex(bytes);
    }

    @Override
    protected int usageLen(byte[] bytes) {
        return BytesUtil.bytesToInt(bytes);
    }

    @Override
    protected byte[] usageId(String id) {
        return BytesUtil.hexToBytes(id);
    }

    @Override
    protected byte[] usageLen(int length) {
        byte[] bytes = BytesUtil.intToBytes(length);
        return bytes.length == this.usageLenLength() ? bytes : BytesUtil.concat(new byte[]{0}, bytes);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.ULV3;
    }
}
