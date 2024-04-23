package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F43", remark = "jcb 43域")
public class Field43 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 22, remark = "Merchant Name", parseType = EBCDIC)
    private String f43f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "space", parseType = EBCDIC)
    private String f43f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 13, remark = "Merchant or ATM Location City", parseType = EBCDIC)
    private String f43f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "space", parseType = EBCDIC)
    private String f43f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 3, remark = "Country Code", parseType = EBCDIC)
    private String f43f5;

    public String getF43f1() {
        return f43f1;
    }

    public void setF43f1(String f43f1) {
        this.f43f1 = f43f1;
    }

    public String getF43f2() {
        return f43f2;
    }

    public void setF43f2(String f43f2) {
        this.f43f2 = f43f2;
    }

    public String getF43f3() {
        return f43f3;
    }

    public void setF43f3(String f43f3) {
        this.f43f3 = f43f3;
    }

    public String getF43f4() {
        return f43f4;
    }

    public void setF43f4(String f43f4) {
        this.f43f4 = f43f4;
    }

    public String getF43f5() {
        return f43f5;
    }

    public void setF43f5(String f43f5) {
        this.f43f5 = f43f5;
    }
}
