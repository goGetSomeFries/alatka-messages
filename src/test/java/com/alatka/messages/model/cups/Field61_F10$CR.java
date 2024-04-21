package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F61_F10", usage = "CR", remark = "银联8583 61.10域usage=CR")
public class Field61_F10$CR implements Field121_F5 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "CAVV校验结果代码")
    private String f61f10f1;

    public String getF61f10f1() {
        return f61f10f1;
    }

    public void setF61f10f1(String f61f10f1) {
        this.f61f10f1 = f61f10f1;
    }
}
