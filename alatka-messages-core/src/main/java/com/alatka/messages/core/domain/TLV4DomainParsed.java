package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class TLV4DomainParsed extends FixedTLVDomainParsed {

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return MessageDefinition.Type.iso == messageDefinition.getType() &&
                messageDefinition.getDomainType() == MessageDefinition.DomainType.TLV4;
    }

    @Override
    protected int tagLength() {
        return 2;
    }

    @Override
    protected int lenLength() {
        return 2;
    }

    @Override
    protected byte[] buildTagBytes(String tag) {
        return BytesUtil.toEBCDIC(tag);
    }

    @Override
    protected byte[] buildLenBytes(String len) {
        return BytesUtil.toEBCDIC(len);
    }

    @Override
    protected int buildLen(byte[] lenBytes) {
        return Integer.parseInt(BytesUtil.fromEBCDIC(lenBytes));
    }

}
