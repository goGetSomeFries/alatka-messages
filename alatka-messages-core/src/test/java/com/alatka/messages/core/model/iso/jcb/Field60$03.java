package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F60", usage = "03", remark = "jcb 60域usage=03")
public class Field60$03 implements Field60 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "STIP Rejected Reason", parseType = EBCDIC)
    private String f60f1;

    public String getF60f1() {
        return f60f1;
    }

    public void setF60f1(String f60f1) {
        this.f60f1 = f60f1;
    }
}
