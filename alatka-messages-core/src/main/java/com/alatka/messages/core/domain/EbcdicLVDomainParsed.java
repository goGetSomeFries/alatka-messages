package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * LV（length-value）域解析器<br>
 * L(EBCDIC)
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class EbcdicLVDomainParsed extends LVDomainParsed {

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return super.matched(messageDefinition, fieldDefinition)
                && super.buildLenParseType(fieldDefinition) == FieldDefinition.ParseType.EBCDIC ;
    }

    @Override
    protected byte[] intToBytes(int length, IsoFieldDefinition fieldDefinition) {
        String str = String.format("%0" + fieldDefinition.getLength() + "d", length);
        return BytesUtil.toEBCDIC(str);
    }

    @Override
    protected int bytesToInt(byte[] lengthBytes, IsoFieldDefinition fieldDefinition) {
        String str = BytesUtil.fromEBCDIC(lengthBytes);
        return Integer.parseInt(str);
    }
}
