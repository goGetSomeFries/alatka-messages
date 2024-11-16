package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import java.time.YearMonth;

import static com.alatka.messages.core.context.MessageDefinition.DomainType.TLV;
import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F63", usage = "TK", domainType = TLV, remark = "银联8583 63域usage=TK")
public class Field63$TK implements Field63 {

    @IsoFieldMeta(domainNo = 1, aliasName = "01", fixed = true, length = 1, remark = "是否验证过Token相关信息")
    private String tag01;
    @IsoFieldMeta(domainNo = 2, aliasName = "02", fixed = false, maxLength = 19, remark = "Token")
    private String tag02;
    @IsoFieldMeta(domainNo = 3, aliasName = "03", fixed = true, length = 4, remark = "Token有效期", pattern = "yyMM")
    private YearMonth tag03;
    @IsoFieldMeta(domainNo = 4, aliasName = "04", fixed = false, maxLength = 2, remark = "Token担保级别")
    private String tag04;
    @IsoFieldMeta(domainNo = 5, aliasName = "05", fixed = true, length = 2, remark = "Token应用场景标识")
    private Integer tag05;
    @IsoFieldMeta(domainNo = 6, aliasName = "06", fixed = false, maxLength = 11, remark = "TRID")
    private String tag06;
    @IsoFieldMeta(domainNo = 7, aliasName = "07", fixed = false, maxLength = 32, remark = "保留使用")
    private byte[] tag07;
    @IsoFieldMeta(domainNo = 8, aliasName = "08", fixed = true, length = 4, remark = "产品标识")
    private byte[] tag08;

    public String getTag01() {
        return tag01;
    }

    public void setTag01(String tag01) {
        this.tag01 = tag01;
    }

    public String getTag02() {
        return tag02;
    }

    public void setTag02(String tag02) {
        this.tag02 = tag02;
    }

    public YearMonth getTag03() {
        return tag03;
    }

    public void setTag03(YearMonth tag03) {
        this.tag03 = tag03;
    }

    public String getTag04() {
        return tag04;
    }

    public void setTag04(String tag04) {
        this.tag04 = tag04;
    }

    public Integer getTag05() {
        return tag05;
    }

    public void setTag05(Integer tag05) {
        this.tag05 = tag05;
    }

    public String getTag06() {
        return tag06;
    }

    public void setTag06(String tag06) {
        this.tag06 = tag06;
    }

    public byte[] getTag07() {
        return tag07;
    }

    public void setTag07(byte[] tag07) {
        this.tag07 = tag07;
    }

    public byte[] getTag08() {
        return tag08;
    }

    public void setTag08(byte[] tag08) {
        this.tag08 = tag08;
    }
}
