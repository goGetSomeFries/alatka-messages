package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F48",
        usage = "RP",
        remark = "银联8583 48域usage=RP")
public class F48RP implements F48 {

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
