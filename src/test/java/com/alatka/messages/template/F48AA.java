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
        usage = "AA",
        remark = "银联8583 48域usage=AA")
public class F48AA implements F48 {

    @IsoFieldMeta(domainNo = 1, fixed = false, length = -1, maxLength = 510, remark = "受理方附加交易信息")
    private String acqInstAddtnlInfo;

    public String getAcqInstAddtnlInfo() {
        return acqInstAddtnlInfo;
    }

    public void setAcqInstAddtnlInfo(String acqInstAddtnlInfo) {
        this.acqInstAddtnlInfo = acqInstAddtnlInfo;
    }
}
