package com.alatka.messages.core.message;

import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

/**
 * 8583 TLV3报文打包/解包器
 *
 * @author ybliu
 */
public class TLV4SubdomainMessageBuilder extends FixedTLVSubdomainMessageBuilder {

    public TLV4SubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
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
    protected String buildTag(byte[] tagBytes) {
        return BytesUtil.fromEBCDIC(tagBytes);
    }

    @Override
    protected int buildLen(byte[] lenBytes) {
        return Integer.parseInt(BytesUtil.fromEBCDIC(lenBytes));
    }
}
