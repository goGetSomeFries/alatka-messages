package com.alatka.messages.model.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F61", remark = "jcb 61域")
public class Field61 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "Mail/Telephone Order Transaction Sign", parseType = EBCDIC)
    private String f61f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "Recurring Transaction Sign", parseType = EBCDIC)
    private String f61f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "Pre-Authorization Sign", parseType = EBCDIC)
    private String f61f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 3, remark = "Terminal Located Country Code", parseType = EBCDIC)
    private String f61f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "Reserved for future use", parseType = EBCDIC)
    private String f61f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 1, remark = "Transaction Initiator Sign", parseType = EBCDIC)
    private String f61f6;

    public String getF61f1() {
        return f61f1;
    }

    public void setF61f1(String f61f1) {
        this.f61f1 = f61f1;
    }

    public String getF61f2() {
        return f61f2;
    }

    public void setF61f2(String f61f2) {
        this.f61f2 = f61f2;
    }

    public String getF61f3() {
        return f61f3;
    }

    public void setF61f3(String f61f3) {
        this.f61f3 = f61f3;
    }

    public String getF61f4() {
        return f61f4;
    }

    public void setF61f4(String f61f4) {
        this.f61f4 = f61f4;
    }

    public String getF61f5() {
        return f61f5;
    }

    public void setF61f5(String f61f5) {
        this.f61f5 = f61f5;
    }

    public String getF61f6() {
        return f61f6;
    }

    public void setF61f6(String f61f6) {
        this.f61f6 = f61f6;
    }
}