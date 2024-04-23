package com.alatka.messages.model.iso.jcb;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.EBCDIC;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = subPayload, domain = "F48", usage = "03", remark = "jcb 48域usage=03")
public class Field48$03 implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 9, remark = "postal code", parseType = EBCDIC)
    private String postalCode;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 5, remark = "street address", parseType = EBCDIC)
    private String streetAddress;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
