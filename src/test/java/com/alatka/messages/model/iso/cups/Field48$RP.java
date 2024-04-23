package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "RP", remark = "银联8583 48域usage=RP")
public class Field48$RP implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 30, remark = "商品代码")
    private String productCode;

    @IsoFieldMeta(domainNo = 2, fixed = true, length = 30, remark = "保留使用")
    private String reversed;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }
}
