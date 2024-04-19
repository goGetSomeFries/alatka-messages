package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.holder.UsageSubdomain;

import static com.alatka.messages.context.MessageDefinition.DomainType.UV;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F121", remark = "银联8583 121域")
public class Field121 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "应答原因码")
    private String f121f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "单/双或双/单转换码")
    private String f121f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "卡性质")
    private String f121f3;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 40, remark = "CUPS保留")
    private String f121f4;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 38, remark = "转入和转出方标识代码/手续费信息", existSubdomain = true, subdomainType = UV)
    private UsageSubdomain<Field121_F5> f121f5;

    public String getF121f1() {
        return f121f1;
    }

    public void setF121f1(String f121f1) {
        this.f121f1 = f121f1;
    }

    public String getF121f2() {
        return f121f2;
    }

    public void setF121f2(String f121f2) {
        this.f121f2 = f121f2;
    }

    public String getF121f3() {
        return f121f3;
    }

    public void setF121f3(String f121f3) {
        this.f121f3 = f121f3;
    }

    public String getF121f4() {
        return f121f4;
    }

    public void setF121f4(String f121f4) {
        this.f121f4 = f121f4;
    }

    public UsageSubdomain<Field121_F5> getF121f5() {
        return f121f5;
    }

    public void setF121f5(UsageSubdomain<Field121_F5> f121f5) {
        this.f121f5 = f121f5;
    }
}
