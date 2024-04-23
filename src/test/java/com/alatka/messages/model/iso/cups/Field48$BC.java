package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "BC", remark = "银联8583 48域usage=BC")
public class Field48$BC implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "风险原因代码")
    private String riskCode;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "风险等级")
    private String riskLevel;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "风险评分")
    private String riskScore;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 12, remark = "保留使用")
    private String reversed;

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(String riskScore) {
        this.riskScore = riskScore;
    }

    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }
}
