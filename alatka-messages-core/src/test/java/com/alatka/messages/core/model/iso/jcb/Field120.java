package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F120", remark = "jcb 120域")
public class Field120 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "Transaction Type", parseType = EBCDIC)
    private String f120f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 16, remark = "JCB Card Number", parseType = EBCDIC)
    private String f120f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 2, remark = "Authorization Action Code", parseType = EBCDIC)
    private String f120f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 6, remark = "Purge Date", parseType = EBCDIC)
    private String f120f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "Stop Region Sign Japan", parseType = EBCDIC)
    private String f120f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 1, remark = "Asia", parseType = EBCDIC)
    private String f120f6;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "USA", parseType = EBCDIC)
    private String f120f7;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 1, remark = "Europe", parseType = EBCDIC)
    private String f120f8;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "Local", parseType = EBCDIC)
    private String f120f9;

    public String getF120f1() {
        return f120f1;
    }

    public void setF120f1(String f120f1) {
        this.f120f1 = f120f1;
    }

    public String getF120f2() {
        return f120f2;
    }

    public void setF120f2(String f120f2) {
        this.f120f2 = f120f2;
    }

    public String getF120f3() {
        return f120f3;
    }

    public void setF120f3(String f120f3) {
        this.f120f3 = f120f3;
    }

    public String getF120f4() {
        return f120f4;
    }

    public void setF120f4(String f120f4) {
        this.f120f4 = f120f4;
    }

    public String getF120f5() {
        return f120f5;
    }

    public void setF120f5(String f120f5) {
        this.f120f5 = f120f5;
    }

    public String getF120f6() {
        return f120f6;
    }

    public void setF120f6(String f120f6) {
        this.f120f6 = f120f6;
    }

    public String getF120f7() {
        return f120f7;
    }

    public void setF120f7(String f120f7) {
        this.f120f7 = f120f7;
    }

    public String getF120f8() {
        return f120f8;
    }

    public void setF120f8(String f120f8) {
        this.f120f8 = f120f8;
    }

    public String getF120f9() {
        return f120f9;
    }

    public void setF120f9(String f120f9) {
        this.f120f9 = f120f9;
    }
}