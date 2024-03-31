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
                && fieldDefinition.getParseType().getLenParseType() == FieldDefinition.ParseType.LPT.B;
    }

    @Override
    protected byte[] intToBytes(int length, FieldDefinition fieldDefinition) {
        length = fieldDefinition.getParseType() == FieldDefinition.ParseType.BCD ? length * 2 : length;
        return BytesUtil.intToBytes(length);
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes, FieldDefinition fieldDefinition) {
        int length = BytesUtil.bytesToInt(lengthBytes);
        return fieldDefinition.getParseType() == FieldDefinition.ParseType.BCD ? length / 2 : length;
    }
}
