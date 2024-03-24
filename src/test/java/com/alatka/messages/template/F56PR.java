package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F56",
        usage = "PR",
        domainType = MessageDefinition.DomainType.TLV,
        remark = "银联8583 56域usage=PR")
public class F56PR implements F56 {

    @IsoFieldMeta(domainNo = 0x01, fixed = false, remark = "PAR")
    private String tag01;

    public String getTag01() {
        return tag01;
    }

    public void setTag01(String tag01) {
        this.tag01 = tag01;
    }
}
