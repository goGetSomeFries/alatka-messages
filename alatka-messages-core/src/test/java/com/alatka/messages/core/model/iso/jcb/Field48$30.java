package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.BCD;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "30", remark = "jcb 48域usage=30")
public class Field48$30 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 8, remark = "Original Transaction ID", parseType = BCD)
    private String f48f1;

    public String getF48f1() {
        return f48f1;
    }

    public void setF48f1(String f48f1) {
        this.f48f1 = f48f1;
    }
}
