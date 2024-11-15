package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "AA", remark = "银联8583 48域usage=AA")
public class Field48$AA implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = false, length = -1, maxLength = 510, remark = "受理方附加交易信息")
    private String acqInstAddtnlInfo;

    public String getAcqInstAddtnlInfo() {
        return acqInstAddtnlInfo;
    }

    public void setAcqInstAddtnlInfo(String acqInstAddtnlInfo) {
        this.acqInstAddtnlInfo = acqInstAddtnlInfo;
    }
}
