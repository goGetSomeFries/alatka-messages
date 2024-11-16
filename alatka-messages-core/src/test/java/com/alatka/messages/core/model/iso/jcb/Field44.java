package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F44", remark = "jcb 44域")
public class Field44 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "CAV2 Result Code", parseType = EBCDIC)
    private String f44f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "AVS Result Code", parseType = EBCDIC)
    private String f44f2;

    public String getF44f1() {
        return f44f1;
    }

    public void setF44f1(String f44f1) {
        this.f44f1 = f44f1;
    }

    public String getF44f2() {
        return f44f2;
    }

    public void setF44f2(String f44f2) {
        this.f44f2 = f44f2;
    }
}