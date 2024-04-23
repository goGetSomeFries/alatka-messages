package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F121_F5", usage = "FD", remark = "银联8583 121.5域usage=FD")
public class Field121_F5$FD implements Field121_F5 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 36, remark = "手续费信息")
    private String f121f5f1;

    public String getF121f5f1() {
        return f121f5f1;
    }

    public void setF121f5f1(String f121f5f1) {
        this.f121f5f1 = f121f5f1;
    }
}
