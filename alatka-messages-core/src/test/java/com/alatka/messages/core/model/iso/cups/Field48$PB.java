package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "PB", remark = "银联8583 48域usage=PB")
public class Field48$PB implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "服务点输入方式码")
    private String serviceId;

    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "IC卡条件代码")
    private String icCode;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIcCode() {
        return icCode;
    }

    public void setIcCode(String icCode) {
        this.icCode = icCode;
    }
}
