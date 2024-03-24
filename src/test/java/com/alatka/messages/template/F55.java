package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.math.BigDecimal;
import java.time.LocalDate;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F55",
        domainType = MessageDefinition.DomainType.TLV,
        remark = "银联8583 55域")
public class F55 {

    @IsoFieldMeta(domainNo = 0x9F26, fixed = false, remark = "应用密文")
    private byte[] crypt;
    @IsoFieldMeta(domainNo = 0x9F27, fixed = false, remark = "密文信息数据")
    private byte[] cryptInfoData;
    @IsoFieldMeta(domainNo = 0x9F10, fixed = false, remark = "发卡行应用数据")
    private byte[] issuerAppData;
    @IsoFieldMeta(domainNo = 0x9F37, fixed = false, remark = "不可预知数")
    private byte[] unpredictableNumber;
    @IsoFieldMeta(domainNo = 0x9F36, fixed = false, remark = "应用交易计数器")
    private byte[] appTransCounter;
    @IsoFieldMeta(domainNo = 0x95, fixed = false, remark = "终端验证结果")
    private byte[] termVerificationResult;
    @IsoFieldMeta(domainNo = 0x9A, fixed = false, remark = "交易日期", pattern = "yyMMdd", parseType = FieldDefinition.ParseType.BCD)
    private LocalDate transDate;
    @IsoFieldMeta(domainNo = 0x9C, fixed = false, remark = "交易类型", parseType = FieldDefinition.ParseType.BCD)
    private Integer transType;
    @IsoFieldMeta(domainNo = 0x9F02, fixed = false, remark = "授权金额", parseType = FieldDefinition.ParseType.BCD)
    private BigDecimal transAmt;

    public byte[] getCrypt() {
        return crypt;
    }

    public void setCrypt(byte[] crypt) {
        this.crypt = crypt;
    }

    public byte[] getCryptInfoData() {
        return cryptInfoData;
    }

    public void setCryptInfoData(byte[] cryptInfoData) {
        this.cryptInfoData = cryptInfoData;
    }

    public byte[] getIssuerAppData() {
        return issuerAppData;
    }

    public void setIssuerAppData(byte[] issuerAppData) {
        this.issuerAppData = issuerAppData;
    }

    public byte[] getUnpredictableNumber() {
        return unpredictableNumber;
    }

    public void setUnpredictableNumber(byte[] unpredictableNumber) {
        this.unpredictableNumber = unpredictableNumber;
    }

    public byte[] getAppTransCounter() {
        return appTransCounter;
    }

    public void setAppTransCounter(byte[] appTransCounter) {
        this.appTransCounter = appTransCounter;
    }

    public byte[] getTermVerificationResult() {
        return termVerificationResult;
    }

    public void setTermVerificationResult(byte[] termVerificationResult) {
        this.termVerificationResult = termVerificationResult;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }
}
