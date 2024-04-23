package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F54", remark = "jcb 54域")
public class Field54 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "Account Type", parseType = EBCDIC)
    private String f54f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "Amount Type", parseType = EBCDIC)
    private String f54f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "Currency Code", parseType = EBCDIC)
    private String f54f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "Credit or Debit Sign", parseType = EBCDIC)
    private String f54f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "Amount", parseType = EBCDIC)
    private String f54f5;

    public String getF54f1() {
        return f54f1;
    }

    public void setF54f1(String f54f1) {
        this.f54f1 = f54f1;
    }

    public String getF54f2() {
        return f54f2;
    }

    public void setF54f2(String f54f2) {
        this.f54f2 = f54f2;
    }

    public String getF54f3() {
        return f54f3;
    }

    public void setF54f3(String f54f3) {
        this.f54f3 = f54f3;
    }

    public String getF54f4() {
        return f54f4;
    }

    public void setF54f4(String f54f4) {
        this.f54f4 = f54f4;
    }

    public String getF54f5() {
        return f54f5;
    }

    public void setF54f5(String f54f5) {
        this.f54f5 = f54f5;
    }
}