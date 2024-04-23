package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F59", usage = "QL", remark = "银联8583 59域usage=QL")
public class Field59$QL implements Field59 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "当前明细顺序号")
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}