package com.alatka.messages.model.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.time.MonthDay;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "21", remark = "jcb 48域usage=21")
public class Field48$21 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 19, remark = "Associated PAN", parseType = EBCDIC)
    private String f48f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 4, remark = "Associated PAN Expiration Date", pattern = "MMdd", parseType = EBCDIC)
    private MonthDay f48f2;

    public String getF48f1() {
        return f48f1;
    }

    public void setF48f1(String f48f1) {
        this.f48f1 = f48f1;
    }

    public MonthDay getF48f2() {
        return f48f2;
    }

    public void setF48f2(MonthDay f48f2) {
        this.f48f2 = f48f2;
    }
}
