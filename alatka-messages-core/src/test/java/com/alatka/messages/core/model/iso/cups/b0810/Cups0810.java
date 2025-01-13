package com.alatka.messages.core.model.iso.cups.b0810;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Map;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "0001",
        code = "0810",
        kind = MessageDefinition.Kind.payload,
        remark = "签到返回")
public class Cups0810 {

    @IsoFieldMeta(domainNo = 0, fixed = true, length = 2, remark = "报文类型", parseType = FieldDefinition.ParseType.BCD)
    private String type;

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 8, remark = "位图", parseType = FieldDefinition.ParseType.BINARY)
    private Map<Integer, Boolean> bitmap;

    @IsoFieldMeta(domainNo = 11, length = 3, remark = "终端交易流水", parseType = FieldDefinition.ParseType.BCD, fixed = true)
    private String traceNumber;

    @IsoFieldMeta(domainNo = 12, length = 3, remark = "受卡方所在地时间", fixed = true, pattern = "HHmmss", parseType = FieldDefinition.ParseType.BCD)
    private LocalTime localTime;

    // 日期MMdd length 就给2
    @IsoFieldMeta(domainNo = 13, length = 2, remark = "受卡方所在地日期", fixed = true, pattern = "MMdd", parseType = FieldDefinition.ParseType.BCD)
    private MonthDay localDate;

    @IsoFieldMeta(domainNo = 32, fixed = false, length = 1, maxLength = 6, remark = "受理方标识码", parseType = FieldDefinition.ParseType.BCD, lenParseType = FieldDefinition.ParseType.BINARY)
    private String acceptorId;

    @IsoFieldMeta(domainNo = 37, fixed = true, length = 12, remark = "检索参考号", parseType = FieldDefinition.ParseType.ASCII)
    private String retrievalReference;

    @IsoFieldMeta(domainNo = 39, fixed = true, length = 2, remark = "应答码")
    private String responseCode;

    @IsoFieldMeta(domainNo = 41, fixed = true, length = 8, remark = "终端号")
    private String terminalId;

    @IsoFieldMeta(domainNo = 42, fixed = true, length = 15, remark = "商户号")
    private String merchantId;

    @IsoFieldMeta(domainNo = 60, length = 6, remark = "自定义域", fixed = true, existSubdomain = true, subdomainType = MessageDefinition.DomainType.FIXED)
    private Field60 custom;

    @IsoFieldMeta(domainNo = 62, length = 5, remark = "终端信息/终端密钥", parseType = FieldDefinition.ParseType.BINARY, fixed = true)
    private String terminalInfo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<Integer, Boolean> getBitmap() {
        return bitmap;
    }

    public void setBitmap(Map<Integer, Boolean> bitmap) {
        this.bitmap = bitmap;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public MonthDay getLocalDate() {
        return localDate;
    }

    public void setLocalDate(MonthDay localDate) {
        this.localDate = localDate;
    }

    public String getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(String acceptorId) {
        this.acceptorId = acceptorId;
    }

    public String getRetrievalReference() {
        return retrievalReference;
    }

    public void setRetrievalReference(String retrievalReference) {
        this.retrievalReference = retrievalReference;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Field60 getCustom() {
        return custom;
    }

    public void setCustom(Field60 custom) {
        this.custom = custom;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }
}
