package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "20", remark = "jcb 48域usage=20")
public class Field48$20 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "Transit Flag", parseType = EBCDIC)
    private String transitFlag;

    public String getTransitFlag() {
        return transitFlag;
    }

    public void setTransitFlag(String transitFlag) {
        this.transitFlag = transitFlag;
    }
}
