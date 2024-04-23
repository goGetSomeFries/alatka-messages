package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.BCD;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F90", remark = "jcb 90域")
public class Field90 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "Original message type identifier", parseType = BCD)
    private String f90f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 3, remark = "Original system trace audit number", parseType = BCD)
    private String f90f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 5, remark = "Original transmission date and time", parseType = BCD)
    private String f90f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 11, remark = "Original acquiring/forwarding institution identification code", parseType = BCD)
    private String f90f4;

    public String getF90f1() {
        return f90f1;
    }

    public void setF90f1(String f90f1) {
        this.f90f1 = f90f1;
    }

    public String getF90f2() {
        return f90f2;
    }

    public void setF90f2(String f90f2) {
        this.f90f2 = f90f2;
    }

    public String getF90f3() {
        return f90f3;
    }

    public void setF90f3(String f90f3) {
        this.f90f3 = f90f3;
    }

    public String getF90f4() {
        return f90f4;
    }

    public void setF90f4(String f90f4) {
        this.f90f4 = f90f4;
    }
}