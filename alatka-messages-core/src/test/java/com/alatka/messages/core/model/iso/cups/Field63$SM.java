package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F63", usage = "SM", remark = "银联8583 63域usage=SM")
public class Field63$SM implements Field63 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 16, remark = "SM4算法加密的PIN数据")
    private byte[] pin;

    public byte[] getPin() {
        return pin;
    }

    public void setPin(byte[] pin) {
        this.pin = pin;
    }
}
