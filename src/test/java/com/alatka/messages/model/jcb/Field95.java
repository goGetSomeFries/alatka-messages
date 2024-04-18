package com.alatka.messages.model.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F95", remark = "jcb 95域")
public class Field95 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 12, remark = "actual transaction amount", parseType = EBCDIC)
    private String f95f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 12, remark = "Actual Amount, reconciliation", parseType = EBCDIC)
    private String f95f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 12, remark = "Actual Amount, cardholder billing", parseType = EBCDIC)
    private String f95f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 6, remark = "reserved", parseType = EBCDIC)
    private String f95f4;

    public String getF95f1() {
        return f95f1;
    }

    public void setF95f1(String f95f1) {
        this.f95f1 = f95f1;
    }

    public String getF95f2() {
        return f95f2;
    }

    public void setF95f2(String f95f2) {
        this.f95f2 = f95f2;
    }

    public String getF95f3() {
        return f95f3;
    }

    public void setF95f3(String f95f3) {
        this.f95f3 = f95f3;
    }

    public String getF95f4() {
        return f95f4;
    }

    public void setF95f4(String f95f4) {
        this.f95f4 = f95f4;
    }
}