package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F60_F11", remark = "银联8583 60.11域")
public class Field60_F11 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "特殊计费类型")
    private String f60f11f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "特殊计费档次")
    private String f60f11f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "保留使用（第3位为MAC算法标识）")
    private String f60f11f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "支持部分承兑和返回余额标志")
    private String f60f11f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "交易发起方式")
    private String f60f11f5;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 1, remark = "交易介质")
    private String f60f11f6;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "IC 卡的应用类型")
    private String f60f11f7;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 2, remark = "账户结算类型")
    private String f60f11f8;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "卡账户等级")
    private String f60f11f9;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 2, remark = "卡产品")
    private String f60f11f10;

    public String getF60f11f1() {
        return f60f11f1;
    }

    public void setF60f11f1(String f60f11f1) {
        this.f60f11f1 = f60f11f1;
    }

    public String getF60f11f2() {
        return f60f11f2;
    }

    public void setF60f11f2(String f60f11f2) {
        this.f60f11f2 = f60f11f2;
    }

    public String getF60f11f3() {
        return f60f11f3;
    }

    public void setF60f11f3(String f60f11f3) {
        this.f60f11f3 = f60f11f3;
    }

    public String getF60f11f4() {
        return f60f11f4;
    }

    public void setF60f11f4(String f60f11f4) {
        this.f60f11f4 = f60f11f4;
    }

    public String getF60f11f5() {
        return f60f11f5;
    }

    public void setF60f11f5(String f60f11f5) {
        this.f60f11f5 = f60f11f5;
    }

    public String getF60f11f6() {
        return f60f11f6;
    }

    public void setF60f11f6(String f60f11f6) {
        this.f60f11f6 = f60f11f6;
    }

    public String getF60f11f7() {
        return f60f11f7;
    }

    public void setF60f11f7(String f60f11f7) {
        this.f60f11f7 = f60f11f7;
    }

    public String getF60f11f8() {
        return f60f11f8;
    }

    public void setF60f11f8(String f60f11f8) {
        this.f60f11f8 = f60f11f8;
    }

    public String getF60f11f9() {
        return f60f11f9;
    }

    public void setF60f11f9(String f60f11f9) {
        this.f60f11f9 = f60f11f9;
    }

    public String getF60f11f10() {
        return f60f11f10;
    }

    public void setF60f11f10(String f60f11f10) {
        this.f60f11f10 = f60f11f10;
    }
}