package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F57", usage = "AB", remark = "银联8583 57域usage=AB")
public class Field57$AB implements Field57 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 20, remark = "发卡方附加交易信息")
    private String addInfo;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 20, remark = "CUPS附加交易信息")
    private String cupsAddInfo;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 56, remark = "保留使用")
    private String reserved;

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getCupsAddInfo() {
        return cupsAddInfo;
    }

    public void setCupsAddInfo(String cupsAddInfo) {
        this.cupsAddInfo = cupsAddInfo;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }
}