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
public class AsciiLVDomainParsed extends LVDomainParsed {

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return super.matched(messageDefinition, fieldDefinition)
                && messageDefinition.getLenParseType() == MessageDefinition.LenParseType.ASCII;
    }

    @Override
    protected byte[] intToBytes(int length, FieldDefinition fieldDefinition) {
        return this.padding(String.valueOf(length).getBytes(), fieldDefinition.getLength(), FieldDefinition.FieldType.NUMBER, fieldDefinition);
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes) {
        return Integer.parseInt(new String(lengthBytes));
    }
}
