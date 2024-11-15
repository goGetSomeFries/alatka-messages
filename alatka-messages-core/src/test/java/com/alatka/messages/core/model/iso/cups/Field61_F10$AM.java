package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F61_F10", usage = "AM", remark = "银联8583 61.10域usage=AM")
public class Field61_F10$AM implements Field121_F5 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 16, remark = "交易校验方式")
    private String f61f10f1;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = -1, maxLength = 147, remark = "业务自定义数据")
    private String f61f10f2;

    public String getF61f10f1() {
        return f61f10f1;
    }

    public void setF61f10f1(String f61f10f1) {
        this.f61f10f1 = f61f10f1;
    }

    public String getF61f10f2() {
        return f61f10f2;
    }

    public void setF61f10f2(String f61f10f2) {
        this.f61f10f2 = f61f10f2;
    }
}
