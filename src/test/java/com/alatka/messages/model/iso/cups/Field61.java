package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.holder.UsageSubdomain;

import static com.alatka.messages.context.MessageDefinition.DomainType.UV;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F61", remark = "银联8583 61域")
public class Field61 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "证件类别")
    private String f61f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 20, remark = "证件编号")
    private String f61f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "CVV校验结果")
    private String cvvResult;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "PVV校验结果")
    private String pvvResult;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 3, remark = "处理中心标志")
    private String f61f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 3, remark = "无卡校验值")
    private String f61f6;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "无卡校验结果")
    private String f61f7;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 1, remark = "ARQC认证结果值")
    private String f61f8;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 3, remark = "安全信息校验值-处理中心标志")
    private String f61f9;
    @IsoFieldMeta(domainNo = 10, fixed = false, length = -1, maxLength = 165, remark = "安全信息校验值-安全认证信息", existSubdomain = true, subdomainType = UV)
    private UsageSubdomain<Field61_F10> f61f10;

    public String getF61f1() {
        return f61f1;
    }

    public void setF61f1(String f61f1) {
        this.f61f1 = f61f1;
    }

    public String getF61f2() {
        return f61f2;
    }

    public void setF61f2(String f61f2) {
        this.f61f2 = f61f2;
    }

    public String getCvvResult() {
        return cvvResult;
    }

    public void setCvvResult(String cvvResult) {
        this.cvvResult = cvvResult;
    }

    public String getPvvResult() {
        return pvvResult;
    }

    public void setPvvResult(String pvvResult) {
        this.pvvResult = pvvResult;
    }

    public String getF61f5() {
        return f61f5;
    }

    public void setF61f5(String f61f5) {
        this.f61f5 = f61f5;
    }

    public String getF61f6() {
        return f61f6;
    }

    public void setF61f6(String f61f6) {
        this.f61f6 = f61f6;
    }

    public String getF61f7() {
        return f61f7;
    }

    public void setF61f7(String f61f7) {
        this.f61f7 = f61f7;
    }

    public String getF61f8() {
        return f61f8;
    }

    public void setF61f8(String f61f8) {
        this.f61f8 = f61f8;
    }

    public String getF61f9() {
        return f61f9;
    }

    public void setF61f9(String f61f9) {
        this.f61f9 = f61f9;
    }

    public UsageSubdomain<Field61_F10> getF61f10() {
        return f61f10;
    }

    public void setF61f10(UsageSubdomain<Field61_F10> f61f10) {
        this.f61f10 = f61f10;
    }
}