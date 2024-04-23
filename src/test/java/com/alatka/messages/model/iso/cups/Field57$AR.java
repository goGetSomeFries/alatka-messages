package com.alatka.messages.model.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F57", usage = "AR", remark = "银联8583 57域usage=AR")
public class Field57$AR implements Field57 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "附加应答信息")
    private String f57f1;

    public String getF57f1() {
        return f57f1;
    }

    public void setF57f1(String f57f1) {
        this.f57f1 = f57f1;
    }
}