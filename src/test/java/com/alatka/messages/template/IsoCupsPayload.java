package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.UsageSubdomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Map;
import java.util.Objects;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        header = IsoCupsHeader.class,
        kind = MessageDefinition.Kind.payload,
        remark = "银联8583报文体")
public class IsoCupsPayload {

    @IsoFieldMeta(domainNo = 0, fixed = true, length = 4, remark = "报文类型")
    private String messageType;
    @IsoFieldMeta(domainNo = 1, fixed = true, length = 16, remark = "位图", parseType = FieldDefinition.ParseType.BINARY)
    private Map<Integer, Boolean> bitmap;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = 2, maxLength = 19, remark = "主账号")
    private String primaryAcctNum;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 6, remark = "交易处理码")
    private String processingCode;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 12, remark = "交易金额")
    private BigDecimal amtTrans;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 12, remark = "清算金额")
    private BigDecimal amtSettlmt;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 12, remark = "持卡人扣账金额")
    private BigDecimal amtCdhldrBil;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 10, remark = "交易传输时间", pattern = "MMddHHmmss")
    private LocalDateTime transmsnDateTime;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 8, remark = "清算汇率")
    private Integer convRateSettlmt;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 8, remark = "持卡人扣账汇率")
    private Integer convRateCdhldrBil;
    @IsoFieldMeta(domainNo = 11, fixed = true, length = 6, remark = "系统跟踪号")
    private Integer sysTraceAuditNum;
    @IsoFieldMeta(domainNo = 12, fixed = true, length = 6, remark = "受卡方所在地时间", pattern = "HHmmss")
    private LocalTime timeLocalTrans;
    @IsoFieldMeta(domainNo = 13, fixed = true, length = 4, remark = "受卡方所在地日期", pattern = "MMdd")
    private MonthDay dateLocalTrans;
    @IsoFieldMeta(domainNo = 48, fixed = false, length = 3, maxLength = 512, remark = "附加数据——私有", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UVAS)
    private UsageSubdomain<F48> addtnlDataPrivate;
    @IsoFieldMeta(domainNo = 53, fixed = true, length = 16, remark = "安全控制信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.DEFAULT)
    private F53 secRelatdCtrlInfo;
    @IsoFieldMeta(domainNo = 55, fixed = false, length = 3, maxLength = 255, remark = "IC卡数据域", existSubdomain = true, subdomainType = MessageDefinition.DomainType.TLV)
    private F55 iccData;
    @IsoFieldMeta(domainNo = 56, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.ULV)
    private UsageSubdomain<F56> addtnlData56;
    @IsoFieldMeta(domainNo = 59, fixed = false, length = 3, maxLength = 600, remark = "明细查询数据", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UV)
    private UsageSubdomain<F59> detailInqrng;
    @IsoFieldMeta(domainNo = 61, fixed = false, length = 3, maxLength = 200, remark = "持卡人身份认证信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.DEFAULT)
    private F61 chAuthInfo;
    @IsoFieldMeta(domainNo = 62, fixed = false, length = 3, maxLength = 200, remark = "交换中心数据", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UV)
    private UsageSubdomain<F62> switchingData;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Map<Integer, Boolean> getBitmap() {
        return bitmap;
    }

    public void setBitmap(Map<Integer, Boolean> bitmap) {
        this.bitmap = bitmap;
    }

    public String getPrimaryAcctNum() {
        return primaryAcctNum;
    }

    public void setPrimaryAcctNum(String primaryAcctNum) {
        this.primaryAcctNum = primaryAcctNum;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public BigDecimal getAmtTrans() {
        return amtTrans;
    }

    public void setAmtTrans(BigDecimal amtTrans) {
        this.amtTrans = amtTrans;
    }

    public BigDecimal getAmtSettlmt() {
        return amtSettlmt;
    }

    public void setAmtSettlmt(BigDecimal amtSettlmt) {
        this.amtSettlmt = amtSettlmt;
    }

    public BigDecimal getAmtCdhldrBil() {
        return amtCdhldrBil;
    }

    public void setAmtCdhldrBil(BigDecimal amtCdhldrBil) {
        this.amtCdhldrBil = amtCdhldrBil;
    }

    public LocalDateTime getTransmsnDateTime() {
        return transmsnDateTime;
    }

    public void setTransmsnDateTime(LocalDateTime transmsnDateTime) {
        this.transmsnDateTime = transmsnDateTime;
    }

    public Integer getConvRateSettlmt() {
        return convRateSettlmt;
    }

    public void setConvRateSettlmt(Integer convRateSettlmt) {
        this.convRateSettlmt = convRateSettlmt;
    }

    public Integer getConvRateCdhldrBil() {
        return convRateCdhldrBil;
    }

    public void setConvRateCdhldrBil(Integer convRateCdhldrBil) {
        this.convRateCdhldrBil = convRateCdhldrBil;
    }

    public Integer getSysTraceAuditNum() {
        return sysTraceAuditNum;
    }

    public void setSysTraceAuditNum(Integer sysTraceAuditNum) {
        this.sysTraceAuditNum = sysTraceAuditNum;
    }

    public LocalTime getTimeLocalTrans() {
        return timeLocalTrans;
    }

    public void setTimeLocalTrans(LocalTime timeLocalTrans) {
        this.timeLocalTrans = timeLocalTrans;
    }

    public MonthDay getDateLocalTrans() {
        return dateLocalTrans;
    }

    public void setDateLocalTrans(MonthDay dateLocalTrans) {
        this.dateLocalTrans = dateLocalTrans;
    }

    public void setAddtnlDataPrivate(UsageSubdomain<F48> addtnlDataPrivate) {
        this.addtnlDataPrivate = addtnlDataPrivate;
    }

    public UsageSubdomain<F48> getAddtnlDataPrivate() {
        return addtnlDataPrivate;
    }

    public F53 getSecRelatdCtrlInfo() {
        return secRelatdCtrlInfo;
    }

    public F55 getIccData() {
        return iccData;
    }

    public void setIccData(F55 iccData) {
        this.iccData = iccData;
    }

    public void setSecRelatdCtrlInfo(F53 secRelatdCtrlInfo) {
        this.secRelatdCtrlInfo = secRelatdCtrlInfo;
    }

    public UsageSubdomain<F56> getAddtnlData56() {
        return addtnlData56;
    }

    public void setAddtnlData56(UsageSubdomain<F56> addtnlData56) {
        this.addtnlData56 = addtnlData56;
    }

    public UsageSubdomain<F59> getDetailInqrng() {
        return detailInqrng;
    }

    public void setDetailInqrng(UsageSubdomain<F59> detailInqrng) {
        this.detailInqrng = detailInqrng;
    }

    public F61 getChAuthInfo() {
        return chAuthInfo;
    }

    public void setChAuthInfo(F61 chAuthInfo) {
        this.chAuthInfo = chAuthInfo;
    }

    public UsageSubdomain<F62> getSwitchingData() {
        return switchingData;
    }

    public void setSwitchingData(UsageSubdomain<F62> switchingData) {
        this.switchingData = switchingData;
    }

    @Override
    public String toString() {
        return "IsoCupsPayload{" +
                "messageType='" + messageType + '\'' +
                ", bitmap=" + bitmap +
                ", primaryAcctNum='" + primaryAcctNum + '\'' +
                ", processingCode='" + processingCode + '\'' +
                ", amtTrans=" + amtTrans +
                ", amtSettlmt=" + amtSettlmt +
                ", amtCdhldrBil=" + amtCdhldrBil +
                ", transmsnDateTime=" + transmsnDateTime +
                ", convRateSettlmt=" + convRateSettlmt +
                ", convRateCdhldrBil=" + convRateCdhldrBil +
                ", sysTraceAuditNum=" + sysTraceAuditNum +
                ", timeLocalTrans=" + timeLocalTrans +
                ", dateLocalTrans=" + dateLocalTrans +
                ", addtnlDataPrivate=" + addtnlDataPrivate +
                ", secRelatdCtrlInfo=" + secRelatdCtrlInfo +
                ", iccData=" + iccData +
                ", addtnlData56=" + addtnlData56 +
                ", detailInqrng=" + detailInqrng +
                ", chAuthInfo=" + chAuthInfo +
                ", switchingData=" + switchingData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsoCupsPayload)) return false;
        IsoCupsPayload that = (IsoCupsPayload) o;
        return Objects.equals(messageType, that.messageType) && Objects.equals(bitmap, that.bitmap) && Objects.equals(primaryAcctNum, that.primaryAcctNum) && Objects.equals(processingCode, that.processingCode) && Objects.equals(amtTrans, that.amtTrans) && Objects.equals(amtSettlmt, that.amtSettlmt) && Objects.equals(amtCdhldrBil, that.amtCdhldrBil) && Objects.equals(transmsnDateTime, that.transmsnDateTime) && Objects.equals(convRateSettlmt, that.convRateSettlmt) && Objects.equals(convRateCdhldrBil, that.convRateCdhldrBil) && Objects.equals(sysTraceAuditNum, that.sysTraceAuditNum) && Objects.equals(timeLocalTrans, that.timeLocalTrans) && Objects.equals(dateLocalTrans, that.dateLocalTrans) && Objects.equals(addtnlDataPrivate, that.addtnlDataPrivate) && Objects.equals(secRelatdCtrlInfo, that.secRelatdCtrlInfo) && Objects.equals(iccData, that.iccData) && Objects.equals(addtnlData56, that.addtnlData56) && Objects.equals(detailInqrng, that.detailInqrng) && Objects.equals(chAuthInfo, that.chAuthInfo) && Objects.equals(switchingData, that.switchingData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageType, bitmap, primaryAcctNum, processingCode, amtTrans, amtSettlmt, amtCdhldrBil, transmsnDateTime, convRateSettlmt, convRateCdhldrBil, sysTraceAuditNum, timeLocalTrans, dateLocalTrans, addtnlDataPrivate, secRelatdCtrlInfo, iccData, addtnlData56, detailInqrng, chAuthInfo, switchingData);
    }
}