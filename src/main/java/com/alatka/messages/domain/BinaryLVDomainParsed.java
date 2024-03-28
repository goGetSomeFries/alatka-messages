package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

/**
 * LV（length-value）域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class BinaryLVDomainParsed extends LVDomainParsed {

    @Override
    public int getOrder() {
        return 30;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return super.matched(messageDefinition, fieldDefinition)
                && messageDefinition.getLenParseType() == MessageDefinition.LenParseType.BINARY;
    }

    @Override
    protected byte[] intToBytes(int length, FieldDefinition fieldDefinition) {
        return BytesUtil.toBytes(length);
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes) {
        return BytesUtil.toInt(lengthBytes);
    }
}
