package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.BINARY;
import static com.alatka.messages.core.context.MessageDefinition.Kind.header;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = header, remark = "jcb报文头")
public class CommonHeader {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "头长度", parseType = BINARY)
    private Integer headerLength;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 2, remark = "保留使用")
    private byte[] reserved;

    public Integer getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(Integer headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }
}
