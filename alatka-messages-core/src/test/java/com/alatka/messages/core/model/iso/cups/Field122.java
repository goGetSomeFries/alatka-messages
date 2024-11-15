package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F122", remark = "银联8583 122域")
public class Field122 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 6, remark = "商户扣率")
    private String mchntRate;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = -1, maxLength = 94, remark = "受理方信息")
    private Long acqInstInfo;

    public String getMchntRate() {
        return mchntRate;
    }

    public void setMchntRate(String mchntRate) {
        this.mchntRate = mchntRate;
    }

    public Long getAcqInstInfo() {
        return acqInstInfo;
    }

    public void setAcqInstInfo(Long acqInstInfo) {
        this.acqInstInfo = acqInstInfo;
    }
}
