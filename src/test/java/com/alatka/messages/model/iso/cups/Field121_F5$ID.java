package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F121_F5", usage = "ID", remark = "银联8583 121.5域usage=ID")
public class Field121_F5$ID implements Field121_F5 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 8, remark = "转出方标识代码")
    private String f121f5f1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 8, remark = "转入方标识代码")
    private String f121f5f2;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 20, remark = "其余")
    private String f121f5f3;

    public String getF121f5f1() {
        return f121f5f1;
    }

    public void setF121f5f1(String f121f5f1) {
        this.f121f5f1 = f121f5f1;
    }

    public String getF121f5f2() {
        return f121f5f2;
    }

    public void setF121f5f2(String f121f5f2) {
        this.f121f5f2 = f121f5f2;
    }

    public String getF121f5f3() {
        return f121f5f3;
    }

    public void setF121f5f3(String f121f5f3) {
        this.f121f5f3 = f121f5f3;
    }
}
