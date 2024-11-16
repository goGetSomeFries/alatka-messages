package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "01", remark = "jcb 48域usage=01")
public class Field48$01 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "cav2", parseType = EBCDIC)
    private String cav2;

    public String getCav2() {
        return cav2;
    }

    public void setCav2(String cav2) {
        this.cav2 = cav2;
    }
}
