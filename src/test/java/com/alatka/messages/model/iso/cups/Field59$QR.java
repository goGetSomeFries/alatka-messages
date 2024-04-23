package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.template.F59QRPage;

import java.util.List;

import static com.alatka.messages.context.MessageDefinition.DomainType.LIST;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F59", usage = "QR", remark = "银联8583 59域usage=QR")
public class Field59$QR implements Field59 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "卡账户货币代码")
    private String currencyCode;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 3, remark = "所有满足查询条件的记录数")
    private Integer counts;
    @IsoFieldMeta(domainNo = 3, fixed = false, length = 50, maxLength = 5000, remark = "查询结果", pageSizeName = "counts", existSubdomain = true, subdomainType = LIST)
    private List<F59QRPage> elements;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<F59QRPage> getElements() {
        return elements;
    }

    public void setElements(List<F59QRPage> elements) {
        this.elements = elements;
    }
}
