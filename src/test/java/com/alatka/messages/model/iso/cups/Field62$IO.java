package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.time.LocalDateTime;

import static com.alatka.messages.context.MessageDefinition.DomainType.TV;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F62", usage = "IO", domainType = TV, remark = "银联8583 62域usage=IO")
public class Field62$IO implements Field62 {

    @IsoFieldMeta(domainNo = 1, aliasName = "T00", fixed = true, length = 3, remark = "国际信用卡公司/外资银行标志")
    private String f62f1;
    @IsoFieldMeta(domainNo = 2, aliasName = "T07", fixed = true, length = 10, remark = "交易日期时间", pattern = "MMddHHmmss")
    private LocalDateTime f62f2;
    @IsoFieldMeta(domainNo = 3, aliasName = "T11", fixed = true, length = 6, remark = "系统跟踪号码")
    private String f62f3;
    @IsoFieldMeta(domainNo = 4, aliasName = "T18", fixed = true, length = 4, remark = "商户类型")
    private String f62f4;
    @IsoFieldMeta(domainNo = 5, aliasName = "T32", fixed = true, length = 11, remark = "受理机构代码")
    private String f62f5;
    @IsoFieldMeta(domainNo = 6, aliasName = "T33", fixed = true, length = 11, remark = "发送机构标识码")
    private String f62f6;
    @IsoFieldMeta(domainNo = 7, aliasName = "T39", fixed = true, length = 2, remark = "响应码")
    private String f62f7;
    @IsoFieldMeta(domainNo = 8, aliasName = "T37", fixed = true, length = 12, remark = "检索参考号码")
    private String f62f8;
    @IsoFieldMeta(domainNo = 9, aliasName = "T41", fixed = true, length = 8, remark = "终端代码")
    private String f62f9;
    @IsoFieldMeta(domainNo = 10, aliasName = "T42", fixed = true, length = 15, remark = "商户代码")
    private String f62f10;
    @IsoFieldMeta(domainNo = 11, aliasName = "T43", fixed = true, length = 40, remark = "商户名称、地址")
    private String f62f11;
    @IsoFieldMeta(domainNo = 12, aliasName = "T60", fixed = true, length = 7, remark = "报文原因码")
    private String f62f12;

    public String getF62f1() {
        return f62f1;
    }

    public void setF62f1(String f62f1) {
        this.f62f1 = f62f1;
    }

    public LocalDateTime getF62f2() {
        return f62f2;
    }

    public void setF62f2(LocalDateTime f62f2) {
        this.f62f2 = f62f2;
    }

    public String getF62f3() {
        return f62f3;
    }

    public void setF62f3(String f62f3) {
        this.f62f3 = f62f3;
    }

    public String getF62f4() {
        return f62f4;
    }

    public void setF62f4(String f62f4) {
        this.f62f4 = f62f4;
    }

    public String getF62f5() {
        return f62f5;
    }

    public void setF62f5(String f62f5) {
        this.f62f5 = f62f5;
    }

    public String getF62f6() {
        return f62f6;
    }

    public void setF62f6(String f62f6) {
        this.f62f6 = f62f6;
    }

    public String getF62f7() {
        return f62f7;
    }

    public void setF62f7(String f62f7) {
        this.f62f7 = f62f7;
    }

    public String getF62f8() {
        return f62f8;
    }

    public void setF62f8(String f62f8) {
        this.f62f8 = f62f8;
    }

    public String getF62f9() {
        return f62f9;
    }

    public void setF62f9(String f62f9) {
        this.f62f9 = f62f9;
    }

    public String getF62f10() {
        return f62f10;
    }

    public void setF62f10(String f62f10) {
        this.f62f10 = f62f10;
    }

    public String getF62f11() {
        return f62f11;
    }

    public void setF62f11(String f62f11) {
        this.f62f11 = f62f11;
    }

    public String getF62f12() {
        return f62f12;
    }

    public void setF62f12(String f62f12) {
        this.f62f12 = f62f12;
    }
}
