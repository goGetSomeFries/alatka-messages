package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.math.BigDecimal;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F28", remark = "jcb 28域")
public class Field28 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "credit or debit", parseType = EBCDIC)
    private String f28f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 8, remark = "fee amount in the currency", parseType = EBCDIC)
    private BigDecimal f28f2;

    public String getF28f1() {
        return f28f1;
    }

    public void setF28f1(String f28f1) {
        this.f28f1 = f28f1;
    }

    public BigDecimal getF28f2() {
        return f28f2;
    }

    public void setF28f2(BigDecimal f28f2) {
        this.f28f2 = f28f2;
    }
}