package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.BCD;
import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "02", remark = "jcb 48域usage=02")
public class Field48$02 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 20, remark = "xid", parseType = BCD)
    private String xid;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 20, remark = "cavv", parseType = BCD)
    private String cavv;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 1, remark = "eci", parseType = BCD)
    private String eci;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 6, remark = "reserved", parseType = EBCDIC)
    private String reserved;

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }
}
