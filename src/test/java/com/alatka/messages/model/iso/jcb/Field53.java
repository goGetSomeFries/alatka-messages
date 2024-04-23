package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.BCD;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F53", remark = "jcb 53域")
public class Field53 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "Key Type", parseType = BCD)
    private String f53f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "PIN Encryption Algorithm", parseType = BCD)
    private String f53f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "PIN Block Format", parseType = BCD)
    private String f53f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "Key Index", parseType = BCD)
    private String f53f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "MAC Index", parseType = BCD)
    private String f53f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 2, remark = "Check Digits", parseType = BCD)
    private String f53f6;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "reserved", parseType = BCD)
    private String f53f7;

    public String getF53f1() {
        return f53f1;
    }

    public void setF53f1(String f53f1) {
        this.f53f1 = f53f1;
    }

    public String getF53f2() {
        return f53f2;
    }

    public void setF53f2(String f53f2) {
        this.f53f2 = f53f2;
    }

    public String getF53f3() {
        return f53f3;
    }

    public void setF53f3(String f53f3) {
        this.f53f3 = f53f3;
    }

    public String getF53f4() {
        return f53f4;
    }

    public void setF53f4(String f53f4) {
        this.f53f4 = f53f4;
    }

    public String getF53f5() {
        return f53f5;
    }

    public void setF53f5(String f53f5) {
        this.f53f5 = f53f5;
    }

    public String getF53f6() {
        return f53f6;
    }

    public void setF53f6(String f53f6) {
        this.f53f6 = f53f6;
    }

    public String getF53f7() {
        return f53f7;
    }

    public void setF53f7(String f53f7) {
        this.f53f7 = f53f7;
    }
}