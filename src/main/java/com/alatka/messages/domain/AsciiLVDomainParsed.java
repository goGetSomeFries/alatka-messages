package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

/**
 * LV（length-value）域解析器<br>
 * L(ASCII)
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
                && fieldDefinition.getParseType().getLenParseType() == FieldDefinition.ParseType.LPT.A;
    }

    @Override
    protected byte[] intToBytes(int length, FieldDefinition fieldDefinition) {
        return String.format("%0" + fieldDefinition.getLength() + "d", length).getBytes();
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes, FieldDefinition fieldDefinition) {
        return Integer.parseInt(new String(lengthBytes));
    }
}
