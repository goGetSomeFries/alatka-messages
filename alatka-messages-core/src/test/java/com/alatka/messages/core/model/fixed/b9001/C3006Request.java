package com.alatka.messages.core.model.fixed.b9001;

import com.alatka.messages.core.annotation.FixedFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

import static com.alatka.messages.core.context.MessageDefinition.Kind.request;
import static com.alatka.messages.core.context.MessageDefinition.Type.fixed;

@MessageMeta(type = fixed, group = "9001", code = "3006", kind = request, remark = "明细查询")
public class C3006Request extends Header {

    @FixedFieldMeta(domainNo = 8, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 9, length = 4, remark = "证件号码", pattern = "yyMM")
    private YearMonth tranYM;
    @FixedFieldMeta(domainNo = 10, length = 1, remark = "币种选项")
    private String option;
    @FixedFieldMeta(domainNo = 11, length = 1, remark = "翻页标志")
    private Integer rtnInd;
    @FixedFieldMeta(domainNo = 12, length = 8, remark = "入账日期", pattern = "yyyyMMdd")
    private LocalDate valDate;
    @FixedFieldMeta(domainNo = 13, length = 8, remark = "记录日期", pattern = "yyyyMMdd")
    private LocalDate purDate;
    @FixedFieldMeta(domainNo = 14, length = 8, remark = "消费时间", pattern = "HHmmssSS")
    private LocalTime purTime;
    @FixedFieldMeta(domainNo = 15, length = 6, remark = "交易流水号")
    private Integer tranNo;
    @FixedFieldMeta(domainNo = 16, length = 1, remark = "是否检查密码标志")
    private String pinFlag;
    @FixedFieldMeta(domainNo = 17, length = 8, remark = "密码")
    private byte[] pin;
    @FixedFieldMeta(domainNo = 18, length = 1, remark = "查询条件")
    private String enqFlag;
    @FixedFieldMeta(domainNo = 19, length = 1, remark = "显示顺序标志")
    private String orderFlag;

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr;
    }

    public YearMonth getTranYM() {
        return tranYM;
    }

    public void setTranYM(YearMonth tranYM) {
        this.tranYM = tranYM;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getRtnInd() {
        return rtnInd;
    }

    public void setRtnInd(Integer rtnInd) {
        this.rtnInd = rtnInd;
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

    public String getPinFlag() {
        return pinFlag;
    }

    public void setPinFlag(String pinFlag) {
        this.pinFlag = pinFlag;
    }

    public byte[] getPin() {
        return pin;
    }

    public void setPin(byte[] pin) {
        this.pin = pin;
    }

    public String getEnqFlag() {
        return enqFlag;
    }

    public void setEnqFlag(String enqFlag) {
        this.enqFlag = enqFlag;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }
}
