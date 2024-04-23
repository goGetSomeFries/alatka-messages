package com.alatka.messages.model.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F60", usage = "02", remark = "jcb 60域usage=02")
public class Field60$02 implements Field60 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "STIP Reason", parseType = EBCDIC)
    private String f60f1;

    public String getF60f1() {
        return f60f1;
    }

    public void setF60f1(String f60f1) {
        this.f60f1 = f60f1;
    }
}
