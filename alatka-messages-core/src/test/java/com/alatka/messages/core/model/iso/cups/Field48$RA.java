package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "RA", remark = "银联8583 48域usage=RA")
public class Field48$RA implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 12, remark = "折扣前标价")
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
