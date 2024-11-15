package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.DomainType.TLV;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F56", usage = "PR", domainType = TLV, remark = "银联8583 56域usage=PR")
public class Field56$PR implements Field56 {

    @IsoFieldMeta(domainNo = 1, aliasName = "01", fixed = true, length = 29, remark = "PAR")
    private String tag01;

    public String getTag01() {
        return tag01;
    }

    public void setTag01(String tag01) {
        this.tag01 = tag01;
    }
}