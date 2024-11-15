package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "CB", remark = "银联8583 48域usage=CB")
public class Field48$CB implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "付款类型")
    private Integer payType;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 30, remark = "付款代码")
    private String payCode;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 30, remark = "付款原因")
    private String payReason;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayReason() {
        return payReason;
    }

    public void setPayReason(String payReason) {
        this.payReason = payReason;
    }
}
