package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.alatka.messages.context.FieldDefinition.ParseType.BCD;
import static com.alatka.messages.context.MessageDefinition.DomainType.TLV;
import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F55", domainType = TLV, remark = "cups 55域")
public class Field55 {

    @IsoFieldMeta(domainNo = 1, aliasName = "9F26", fixed = true, length = 8, remark = "应用密文")
    private byte[] crypt;
    @IsoFieldMeta(domainNo = 2, aliasName = "9F27", fixed = true, length = 1, remark = "密文信息数据")
    private byte[] cryptInfoData;
    @IsoFieldMeta(domainNo = 3, aliasName = "9F10", fixed = false, maxLength = 32, remark = "发卡行应用数据")
    private byte[] issuerAppData;
    @IsoFieldMeta(domainNo = 4, aliasName = "9F37", fixed = true, length = 4, remark = "不可预知数")
    private byte[] unpredictableNumber;
    @IsoFieldMeta(domainNo = 5, aliasName = "9F36", fixed = true, length = 2, remark = "应用交易计数器")
    private byte[] appTransCounter;
    @IsoFieldMeta(domainNo = 6, aliasName = "95", fixed = true, length = 5, remark = "终端验证结果")
    private byte[] termVerificationResult;
    @IsoFieldMeta(domainNo = 7, aliasName = "9A", fixed = true, length = 3, remark = "交易日期", pattern = "yyMMdd", parseType = BCD)
    private LocalDate transDate;
    @IsoFieldMeta(domainNo = 8, aliasName = "9C", fixed = true, length = 1, remark = "交易类型", parseType = BCD)
    private Integer transType;
    @IsoFieldMeta(domainNo = 9, aliasName = "9F02", fixed = true, length = 6, remark = "授权金额", parseType = BCD)
    private BigDecimal transAmt;
    @IsoFieldMeta(domainNo = 10, aliasName = "5F2A", fixed = true, length = 2, remark = "交易货币代码", parseType = BCD)
    private Integer transCurrencyCode;
    @IsoFieldMeta(domainNo = 11, aliasName = "82", fixed = true, length = 2, remark = "应用交互特征")
    private byte[] appInterchangeProfile;
    @IsoFieldMeta(domainNo = 12, aliasName = "9F1A", fixed = true, length = 2, remark = "终端国家代码", parseType = BCD)
    private Integer termCountryCode;
    @IsoFieldMeta(domainNo = 13, aliasName = "9F03", fixed = true, length = 6, remark = "其它金额", parseType = BCD)
    private BigDecimal otherAmt;
    @IsoFieldMeta(domainNo = 14, aliasName = "9F34", fixed = true, length = 3, remark = "持卡人验证方法结果")
    private byte[] cardholderVerificationMethodResults;
    @IsoFieldMeta(domainNo = 15, aliasName = "9F35", fixed = true, length = 1, remark = "终端类型", parseType = BCD)
    private Integer termType;
    @IsoFieldMeta(domainNo = 16, aliasName = "9F09", fixed = true, length = 2, remark = "应用版本号")
    private byte[] termAppVersionNumber;
    @IsoFieldMeta(domainNo = 17, aliasName = "9F33", fixed = true, length = 3, remark = "终端性能")
    private byte[] termCap;
    @IsoFieldMeta(domainNo = 18, aliasName = "9F1E", fixed = true, length = 8, remark = "接口设备序列号")
    private String interfaceDeviceSerialNumber;
    @IsoFieldMeta(domainNo = 19, aliasName = "4F", fixed = false, maxLength = 16, remark = "icc application ID")
    private byte[] iccAppId;
    @IsoFieldMeta(domainNo = 20, aliasName = "9F41", fixed = false, maxLength = 4, remark = "交易序列计数器", parseType = BCD)
    private Integer transSequenceCounter;
    @IsoFieldMeta(domainNo = 21, aliasName = "9F7C", fixed = false, maxLength = 32, remark = "Partner Discretionary Data")
    private byte[] pdd;
    @IsoFieldMeta(domainNo = 22, aliasName = "84", fixed = false, maxLength = 16, remark = "专用文件名称")
    private byte[] dedicatedFileName;
    @IsoFieldMeta(domainNo = 23, aliasName = "91", fixed = false, maxLength = 16, remark = "发卡行认证数据")
    private byte[] issuerAuthenticationData;
    @IsoFieldMeta(domainNo = 24, aliasName = "71", fixed = false, maxLength = 128, remark = "发卡行脚本1")
    private byte[] issuerScriptTemplate1;
    @IsoFieldMeta(domainNo = 25, aliasName = "72", fixed = false, maxLength = 128, remark = "发卡行脚本2")
    private byte[] issuerScriptTemplate2;

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

    public Integer getTransCurrencyCode() {
        return transCurrencyCode;
    }

    public void setTransCurrencyCode(Integer transCurrencyCode) {
        this.transCurrencyCode = transCurrencyCode;
    }

    public byte[] getAppInterchangeProfile() {
        return appInterchangeProfile;
    }

    public void setAppInterchangeProfile(byte[] appInterchangeProfile) {
        this.appInterchangeProfile = appInterchangeProfile;
    }

    public Integer getTermCountryCode() {
        return termCountryCode;
    }

    public void setTermCountryCode(Integer termCountryCode) {
        this.termCountryCode = termCountryCode;
    }

    public BigDecimal getOtherAmt() {
        return otherAmt;
    }

    public void setOtherAmt(BigDecimal otherAmt) {
        this.otherAmt = otherAmt;
    }

    public byte[] getCardholderVerificationMethodResults() {
        return cardholderVerificationMethodResults;
    }

    public void setCardholderVerificationMethodResults(byte[] cardholderVerificationMethodResults) {
        this.cardholderVerificationMethodResults = cardholderVerificationMethodResults;
    }

    public Integer getTermType() {
        return termType;
    }

    public void setTermType(Integer termType) {
        this.termType = termType;
    }

    public byte[] getTermAppVersionNumber() {
        return termAppVersionNumber;
    }

    public void setTermAppVersionNumber(byte[] termAppVersionNumber) {
        this.termAppVersionNumber = termAppVersionNumber;
    }

    public byte[] getTermCap() {
        return termCap;
    }

    public void setTermCap(byte[] termCap) {
        this.termCap = termCap;
    }

    public String getInterfaceDeviceSerialNumber() {
        return interfaceDeviceSerialNumber;
    }

    public void setInterfaceDeviceSerialNumber(String interfaceDeviceSerialNumber) {
        this.interfaceDeviceSerialNumber = interfaceDeviceSerialNumber;
    }

    public byte[] getIccAppId() {
        return iccAppId;
    }

    public void setIccAppId(byte[] iccAppId) {
        this.iccAppId = iccAppId;
    }

    public Integer getTransSequenceCounter() {
        return transSequenceCounter;
    }

    public void setTransSequenceCounter(Integer transSequenceCounter) {
        this.transSequenceCounter = transSequenceCounter;
    }

    public byte[] getPdd() {
        return pdd;
    }

    public void setPdd(byte[] pdd) {
        this.pdd = pdd;
    }

    public byte[] getDedicatedFileName() {
        return dedicatedFileName;
    }

    public void setDedicatedFileName(byte[] dedicatedFileName) {
        this.dedicatedFileName = dedicatedFileName;
    }

    public byte[] getIssuerAuthenticationData() {
        return issuerAuthenticationData;
    }

    public void setIssuerAuthenticationData(byte[] issuerAuthenticationData) {
        this.issuerAuthenticationData = issuerAuthenticationData;
    }

    public byte[] getIssuerScriptTemplate1() {
        return issuerScriptTemplate1;
    }

    public void setIssuerScriptTemplate1(byte[] issuerScriptTemplate1) {
        this.issuerScriptTemplate1 = issuerScriptTemplate1;
    }

    public byte[] getIssuerScriptTemplate2() {
        return issuerScriptTemplate2;
    }

    public void setIssuerScriptTemplate2(byte[] issuerScriptTemplate2) {
        this.issuerScriptTemplate2 = issuerScriptTemplate2;
    }
}