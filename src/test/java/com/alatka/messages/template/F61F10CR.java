package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F61_F10",
        usage = "CR",
        remark = "银联8583 61_10域usage=CR")
public class F61F10CR implements F61F10 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "CAVV校验结果代码")
    private String f61f10f1;

    public String getF61f10f1() {
        return f61f10f1;
    }

    public void setF61f10f1(String f61f10f1) {
        this.f61f10f1 = f61f10f1;
    }
}
