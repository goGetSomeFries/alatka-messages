package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F53",
        remark = "银联8583 53域")
public class F53 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "重置密钥的类型/PIN格式")
    private String keyType;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "加密算法标志")
    private String encryptionMethodUsed;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 14, remark = "保留使用")
    private Long reserved;

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getEncryptionMethodUsed() {
        return encryptionMethodUsed;
    }

    public void setEncryptionMethodUsed(String encryptionMethodUsed) {
        this.encryptionMethodUsed = encryptionMethodUsed;
    }

    public Long getReserved() {
        return reserved;
    }

    public void setReserved(Long reserved) {
        this.reserved = reserved;
    }
}
