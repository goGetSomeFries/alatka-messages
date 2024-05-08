package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.DomainType.FIXED;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F60", remark = "银联8583 60域")
public class Field60 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 4, remark = "报文原因码")
    private String f60f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "账户所有人类型")
    private String f60f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "终端读取能力")
    private String f60f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "IC卡条件代码")
    private String f60f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "保留使用")
    private String f60f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 2, remark = "终端类型")
    private String f60f6;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "受理免验密码标志")
    private String f60f7;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 1, remark = "IC卡验证可靠性标志")
    private String f60f8;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 2, remark = "电子商务标志")
    private String f60f9;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 1, remark = "交互方式标志")
    private String f60f10;
    @IsoFieldMeta(domainNo = 11, fixed = true, length = -1, maxLength = 15, remark = "交易发生附加信息", existSubdomain = true, subdomainType = FIXED)
    private Field60_F11 f60f11;

    public String getF60f1() {
        return f60f1;
    }

    public void setF60f1(String f60f1) {
        this.f60f1 = f60f1;
    }

    public String getF60f2() {
        return f60f2;
    }

    public void setF60f2(String f60f2) {
        this.f60f2 = f60f2;
    }

    public String getF60f3() {
        return f60f3;
    }

    public void setF60f3(String f60f3) {
        this.f60f3 = f60f3;
    }

    public String getF60f4() {
        return f60f4;
    }

    public void setF60f4(String f60f4) {
        this.f60f4 = f60f4;
    }

    public String getF60f5() {
        return f60f5;
    }

    public void setF60f5(String f60f5) {
        this.f60f5 = f60f5;
    }

    public String getF60f6() {
        return f60f6;
    }

    public void setF60f6(String f60f6) {
        this.f60f6 = f60f6;
    }

    public String getF60f7() {
        return f60f7;
    }

    public void setF60f7(String f60f7) {
        this.f60f7 = f60f7;
    }

    public String getF60f8() {
        return f60f8;
    }

    public void setF60f8(String f60f8) {
        this.f60f8 = f60f8;
    }

    public String getF60f9() {
        return f60f9;
    }

    public void setF60f9(String f60f9) {
        this.f60f9 = f60f9;
    }

    public String getF60f10() {
        return f60f10;
    }

    public void setF60f10(String f60f10) {
        this.f60f10 = f60f10;
    }

    public Field60_F11 getF60f11() {
        return f60f11;
    }

    public void setF60f11(Field60_F11 f60f11) {
        this.f60f11 = f60f11;
    }
}