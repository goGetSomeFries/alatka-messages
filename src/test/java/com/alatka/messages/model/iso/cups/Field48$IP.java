package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "IP", remark = "银联8583 48域usage=IP")
public class Field48$IP implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "期数")
    private Integer instalments;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 30, remark = "保留使用")
    private String reversed;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "保留使用")
    private String reversed1;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "保留使用")
    private String reversed2;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 6, remark = "分期付款手续费率")
    private Integer installmentFeeRate;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 6, remark = "商户补贴手续费率")
    private Integer MerchantFeeRate;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 16, remark = "保留使用")
    private String reversed3;

    public Integer getInstalments() {
        return instalments;
    }

    public void setInstalments(Integer instalments) {
        this.instalments = instalments;
    }

    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }

    public String getReversed1() {
        return reversed1;
    }

    public void setReversed1(String reversed1) {
        this.reversed1 = reversed1;
    }

    public String getReversed2() {
        return reversed2;
    }

    public void setReversed2(String reversed2) {
        this.reversed2 = reversed2;
    }

    public Integer getInstallmentFeeRate() {
        return installmentFeeRate;
    }

    public void setInstallmentFeeRate(Integer installmentFeeRate) {
        this.installmentFeeRate = installmentFeeRate;
    }

    public Integer getMerchantFeeRate() {
        return MerchantFeeRate;
    }

    public void setMerchantFeeRate(Integer merchantFeeRate) {
        MerchantFeeRate = merchantFeeRate;
    }

    public String getReversed3() {
        return reversed3;
    }

    public void setReversed3(String reversed3) {
        this.reversed3 = reversed3;
    }
}

