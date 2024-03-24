package com.alatka.messages.template;

import com.alatka.messages.annotation.FixedFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Objects;

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "3006",
        kind = MessageDefinition.Kind.subPayload,
        remark = "信用卡账单明细查询")
public class Fixed3006Page {
    @FixedFieldMeta(domainNo = 1, length = 3, remark = "月份序号")
    private Integer monthNbr;
    @FixedFieldMeta(domainNo = 2, length = 8, remark = "入账日期", pattern = "yyyyMMdd")
    private LocalDate valDate;
    @FixedFieldMeta(domainNo = 3, length = 8, remark = "记录日期", pattern = "yyyyMMdd")
    private LocalDate purDate;
    @FixedFieldMeta(domainNo = 4, length = 8, remark = "消费时间", pattern = "HHmmssSS")
    private LocalTime purTime;
    @FixedFieldMeta(domainNo = 5, length = 6, remark = "交易流水号")
    private Integer tranNo;
    @FixedFieldMeta(domainNo = 6, length = 4, remark = "交易类型")
    private String tranType;
    @FixedFieldMeta(domainNo = 7, length = 12, remark = "交易金额")
    private BigDecimal amount;
    @FixedFieldMeta(domainNo = 8, length = 1, remark = "交易金额符号")
    private String amountFl;
    @FixedFieldMeta(domainNo = 9, length = 6, remark = "授权代码")
    private String authCode;
    @FixedFieldMeta(domainNo = 10, length = 42, remark = "交易描述1")
    private String desline1;
    @FixedFieldMeta(domainNo = 11, length = 25, remark = "交易描述2")
    private String desline2;
    @FixedFieldMeta(domainNo = 12, length = 4, remark = "卡号后四位")
    private String cardEnd;
    @FixedFieldMeta(domainNo = 13, length = 3, remark = "货币代码")
    private String currNum;
    @FixedFieldMeta(domainNo = 14, length = 1, remark = "保留字段1")
    private String mpFlag;
    @FixedFieldMeta(domainNo = 15, length = 4, remark = "消费日期", pattern = "MMdd")
    private MonthDay purDate1;
    @FixedFieldMeta(domainNo = 16, length = 1, remark = "撤销冲正标志")
    private String revInd;
    @FixedFieldMeta(domainNo = 17, length = 4, remark = "商户类别")
    private String mcc;
    @FixedFieldMeta(domainNo = 18, length = 1, remark = "人脸支付标志")
    private String facePayFlag;

    public Integer getMonthNbr() {
        return monthNbr;
    }

    public void setMonthNbr(Integer monthNbr) {
        this.monthNbr = monthNbr;
    }

    public LocalDate getValDate() {
        return valDate;
    }

    public void setValDate(LocalDate valDate) {
        this.valDate = valDate;
    }

    public LocalDate getPurDate() {
        return purDate;
    }

    public void setPurDate(LocalDate purDate) {
        this.purDate = purDate;
    }

    public LocalTime getPurTime() {
        return purTime;
    }

    public void setPurTime(LocalTime purTime) {
        this.purTime = purTime;
    }

    public Integer getTranNo() {
        return tranNo;
    }

    public void setTranNo(Integer tranNo) {
        this.tranNo = tranNo;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAmountFl() {
        return amountFl;
    }

    public void setAmountFl(String amountFl) {
        this.amountFl = amountFl;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getDesline1() {
        return desline1;
    }

    public void setDesline1(String desline1) {
        this.desline1 = desline1;
    }

    public String getDesline2() {
        return desline2;
    }

    public void setDesline2(String desline2) {
        this.desline2 = desline2;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }

    public String getCurrNum() {
        return currNum;
    }

    public void setCurrNum(String currNum) {
        this.currNum = currNum;
    }

    public String getMpFlag() {
        return mpFlag;
    }

    public void setMpFlag(String mpFlag) {
        this.mpFlag = mpFlag;
    }

    public MonthDay getPurDate1() {
        return purDate1;
    }

    public void setPurDate1(MonthDay purDate1) {
        this.purDate1 = purDate1;
    }

    public String getRevInd() {
        return revInd;
    }

    public void setRevInd(String revInd) {
        this.revInd = revInd;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getFacePayFlag() {
        return facePayFlag;
    }

    public void setFacePayFlag(String facePayFlag) {
        this.facePayFlag = facePayFlag;
    }

    @Override
    public String toString() {
        return "Fixed3006Page{" +
                "monthNbr=" + monthNbr +
                ", valDate=" + valDate +
                ", purDate=" + purDate +
                ", purTime=" + purTime +
                ", tranNo=" + tranNo +
                ", tranType='" + tranType + '\'' +
                ", amount=" + amount +
                ", amountFl='" + amountFl + '\'' +
                ", authCode='" + authCode + '\'' +
                ", desline1='" + desline1 + '\'' +
                ", desline2='" + desline2 + '\'' +
                ", cardEnd='" + cardEnd + '\'' +
                ", currNum='" + currNum + '\'' +
                ", mpFlag='" + mpFlag + '\'' +
                ", purDate1=" + purDate1 +
                ", revInd=" + revInd +
                ", mcc='" + mcc + '\'' +
                ", facePayFlag=" + facePayFlag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fixed3006Page)) return false;
        Fixed3006Page that = (Fixed3006Page) o;
        return Objects.equals(monthNbr, that.monthNbr) &&
                Objects.equals(valDate, that.valDate) &&
                Objects.equals(purDate, that.purDate) &&
                // pattern = "HHmmssSS" 导致毫秒精度丢失
//                Objects.equals(purTime, that.purTime) &&
                Objects.equals(tranNo, that.tranNo) &&
                Objects.equals(tranType, that.tranType) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(amountFl, that.amountFl) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(desline1, that.desline1) &&
                Objects.equals(desline2, that.desline2) &&
                Objects.equals(cardEnd, that.cardEnd) &&
                Objects.equals(currNum, that.currNum) &&
                Objects.equals(mpFlag, that.mpFlag) &&
                Objects.equals(purDate1, that.purDate1) &&
                Objects.equals(revInd, that.revInd) &&
                Objects.equals(mcc, that.mcc) &&
                Objects.equals(facePayFlag, that.facePayFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthNbr, valDate, purDate, /*purTime, */tranNo, tranType, amount, amountFl, authCode, desline1, desline2, cardEnd, currNum, mpFlag, purDate1, revInd, mcc, facePayFlag);
    }
}
