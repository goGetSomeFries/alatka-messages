package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "IN", remark = "银联8583 48域usage=IN")
public class Field48$IN implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = false, length = -1, maxLength = 255, remark = "CUPSecure 认证信息")
    private String cupSecureInfo;

    public String getCupSecureInfo() {
        return cupSecureInfo;
    }

    public void setCupSecureInfo(String cupSecureInfo) {
        this.cupSecureInfo = cupSecureInfo;
    }
}
