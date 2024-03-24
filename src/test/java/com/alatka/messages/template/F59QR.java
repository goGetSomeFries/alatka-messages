package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

import java.util.List;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F59",
        usage = "QR",
        remark = "银联8583 59域usage=QR")
public class F59QR implements F59 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "卡账户货币代码")
    private String currencyCode;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 3, remark = "所有满足查询条件的记录数")
    private Integer counts;
    @IsoFieldMeta(domainNo = 3, fixed = false, length = 50, maxLength = 5000, remark = "查询结果", pageSizeName = "counts", existSubdomain = true, subdomainType = MessageDefinition.DomainType.LIST)
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
