package com.alatka.messages.model.fixed.b9001;

import com.alatka.messages.annotation.FixedFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.fixed;

@MessageMeta(type = fixed, group = "9001", code = "3006", kind = subPayload, remark = "明细查询")
public class C3006Page {
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

}
