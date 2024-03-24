package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

import java.time.LocalDate;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F59@QR",
        remark = "银联8583 59域usage=QR page")
public class F59QRPage {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 3, remark = "明细顺序号")
    private Integer number;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 8, remark = "交易日期", pattern = "yyyyMMdd")
    private LocalDate transDate;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "交易货币代码")
    private String currencyCode;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 13, remark = "交易金额")
    private String transAmt;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 13, remark = "余额")
    private String balanceAmt;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 10, remark = "备注代码")
    private String memoCode;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public String getMemoCode() {
        return memoCode;
    }

    public void setMemoCode(String memoCode) {
        this.memoCode = memoCode;
    }
}
