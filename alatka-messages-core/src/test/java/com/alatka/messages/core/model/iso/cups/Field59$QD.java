package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import java.time.LocalDate;

import static com.alatka.messages.core.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F59", usage = "QD", remark = "银联8583 59域usage=QD")
public class Field59$QD implements Field59 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "当前明细顺序号")
    private Integer number;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 8, remark = "明细起始日期", pattern = "yyyyMMdd")
    private LocalDate beginDate;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 8, remark = "明细中止日期", pattern = "yyyyMMdd")
    private LocalDate endDate;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}