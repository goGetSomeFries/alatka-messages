package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "NK", remark = "银联8583 48域usage=NK")
public class Field48$NK implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 510, remark = "新密钥")
    private byte[] newKey;

    public byte[] getNewKey() {
        return newKey;
    }

    public void setNewKey(byte[] newKey) {
        this.newKey = newKey;
    }
}
