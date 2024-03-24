package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F63.TK",
        remark = "银联8583 63域")
public class F63TK implements F63 {

    @IsoFieldMeta(domainNo = 0, fixed = true, length = 4, remark = "报文类型")
    private String messageType;

}
