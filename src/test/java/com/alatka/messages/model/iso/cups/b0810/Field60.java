package com.alatka.messages.model.iso.cups.b0810;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "0001",
        code = "0810",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F60",
        remark = "银联60域")
public class Field60 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "自定义域", parseType = FieldDefinition.ParseType.BCD)
    private String reserved;

    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "交易类型码", parseType = FieldDefinition.ParseType.BCD)
    private String type;

    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "批次号", parseType = FieldDefinition.ParseType.BCD)
    private String batchNo;

    @IsoFieldMeta(domainNo = 4, fixed = true, length = 3, remark = "网络管理信息n3", parseType = FieldDefinition.ParseType.BCD)
    private byte[] netManagerMessage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public byte[] getNetManagerMessage() {
        return netManagerMessage;
    }

    public void setNetManagerMessage(byte[] netManagerMessage) {
        this.netManagerMessage = netManagerMessage;
    }
}
