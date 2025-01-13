package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * LV（length-value）域解析器
 * L(BINARY)
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class BinaryLVDomainParsed extends LVDomainParsed {

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return super.matched(messageDefinition, fieldDefinition)
                && super.buildLenParseType(fieldDefinition) == FieldDefinition.ParseType.BINARY;
    }

    @Override
    protected byte[] intToBytes(int length, IsoFieldDefinition fieldDefinition) {
        byte[] lenBytes = BytesUtil.intToBytes(length);
        byte[] fillBytes = new byte[fieldDefinition.getLength() - lenBytes.length];
        return BytesUtil.concat(fillBytes, lenBytes);
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes, IsoFieldDefinition fieldDefinition) {
        return BytesUtil.bytesToInt(lengthBytes);
    }
}
