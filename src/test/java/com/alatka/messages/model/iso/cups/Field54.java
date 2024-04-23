package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.math.BigDecimal;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F54", remark = "银联8583 54域")
public class Field54 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "账户类型")
    private String accountType1;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 2, remark = "余额类型")
    private String balanceType1;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "货币代码")
    private String currencyCode1;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 1, remark = "余额符号")
    private String balanceCode1;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 12, remark = "余额")
    private BigDecimal balance1;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 2, remark = "账户类型")
    private String accountType2;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 2, remark = "余额类型")
    private String balanceType2;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 3, remark = "货币代码")
    private String currencyCode2;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "余额符号")
    private String balanceCode2;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 12, remark = "余额")
    private BigDecimal balance2;

    public String getAccountType1() {
        return accountType1;
    }

    public void setAccountType1(String accountType1) {
        this.accountType1 = accountType1;
    }

    public String getBalanceType1() {
        return balanceType1;
    }

    public void setBalanceType1(String balanceType1) {
        this.balanceType1 = balanceType1;
    }

    public String getCurrencyCode1() {
        return currencyCode1;
    }

    public void setCurrencyCode1(String currencyCode1) {
        this.currencyCode1 = currencyCode1;
    }

    public String getBalanceCode1() {
        return balanceCode1;
    }

    public void setBalanceCode1(String balanceCode1) {
        this.balanceCode1 = balanceCode1;
    }

    public BigDecimal getBalance1() {
        return balance1;
    }

    public void setBalance1(BigDecimal balance1) {
        this.balance1 = balance1;
    }

    public String getAccountType2() {
        return accountType2;
    }

    public void setAccountType2(String accountType2) {
        this.accountType2 = accountType2;
    }

    public String getBalanceType2() {
        return balanceType2;
    }

    public void setBalanceType2(String balanceType2) {
        this.balanceType2 = balanceType2;
    }

    public String getCurrencyCode2() {
        return currencyCode2;
    }

    public void setCurrencyCode2(String currencyCode2) {
        this.currencyCode2 = currencyCode2;
    }

    public String getBalanceCode2() {
        return balanceCode2;
    }

    public void setBalanceCode2(String balanceCode2) {
        this.balanceCode2 = balanceCode2;
    }

    public BigDecimal getBalance2() {
        return balance2;
    }

    public void setBalance2(BigDecimal balance2) {
        this.balance2 = balance2;
    }
}
