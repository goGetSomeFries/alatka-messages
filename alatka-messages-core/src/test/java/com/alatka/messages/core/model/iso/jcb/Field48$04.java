package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "04", remark = "jcb 48域usage=04")
public class Field48$04 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "pos data extended condition code", parseType = EBCDIC)
    private String posData;

    public String getPosData() {
        return posData;
    }

    public void setPosData(String posData) {
        this.posData = posData;
    }
}
