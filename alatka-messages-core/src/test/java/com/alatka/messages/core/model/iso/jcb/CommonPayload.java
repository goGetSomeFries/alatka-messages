package com.alatka.messages.core.model.iso.jcb;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.holder.Bitmap;
import com.alatka.messages.core.holder.UsageSubdomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.*;
import static com.alatka.messages.core.context.FieldDefinition.Status.RAW;
import static com.alatka.messages.core.context.MessageDefinition.DomainType.*;
import static com.alatka.messages.core.context.MessageDefinition.Kind.payload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "jcb", code = "common", kind = payload, remark = "jcb报文体")
public class CommonPayload {

    @IsoFieldMeta(domainNo = 0, fixed = true, length = 4, remark = "报文类型", parseType = EBCDIC)
    private String messageType;
    @IsoFieldMeta(domainNo = 1, fixed = false, length = 16, remark = "位图", parseType = BINARY)
    private Bitmap bitmap;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = 1, maxLength = 10, remark = "主账号", parseType = BCD, lenParseType = BINARY)
    private String pan;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 3, remark = "交易处理码", parseType = BCD)
    private String processingCode;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 6, remark = "交易金额", parseType = BCD)
    private BigDecimal amtTrans;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 6, remark = "清算金额", parseType = BCD)
    private BigDecimal amtSettlmt;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 6, remark = "持卡人扣账金额", parseType = BCD)
    private BigDecimal amtCdhldrBil;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 5, remark = "交易传输时间", pattern = "MMddHHmmss", parseType = BCD)
    private LocalDateTime transmsnDateTime;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 4, remark = "清算汇率", parseType = BCD)
    private Integer convRateSettlmt;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 4, remark = "持卡人扣账汇率", parseType = BCD)
    private Integer convRateCdhldrBil;
    @IsoFieldMeta(domainNo = 11, fixed = true, length = 3, remark = "系统跟踪号", parseType = BCD)
    private String sysTraceAuditNum;
    @IsoFieldMeta(domainNo = 12, fixed = true, length = 3, remark = "受卡方所在地时间", pattern = "HHmmss", parseType = BCD)
    private LocalTime timeLocalTrans;
    @IsoFieldMeta(domainNo = 13, fixed = true, length = 2, remark = "受卡方所在地日期", pattern = "MMdd", parseType = BCD)
    private MonthDay dateLocalTrans;
    @IsoFieldMeta(domainNo = 14, fixed = true, length = 2, remark = "卡有效期", pattern = "yyMM", parseType = BCD)
    private YearMonth dateExpr;
    @IsoFieldMeta(domainNo = 16, fixed = true, length = 2, remark = "兑换日期", pattern = "MMdd", parseType = BCD)
    private MonthDay dateConv;
    @IsoFieldMeta(domainNo = 18, fixed = true, length = 2, remark = "商户类型", parseType = BCD)
    private String mchntType;
    @IsoFieldMeta(domainNo = 22, fixed = true, length = 2, remark = "服务点输入方式码", parseType = BCD)
    private String posEntryModeCode;
    @IsoFieldMeta(domainNo = 23, fixed = true, length = 2, remark = "卡序列号", parseType = BCD)
    private String cardSeqId;
    @IsoFieldMeta(domainNo = 25, fixed = true, length = 1, remark = "服务点条件码", parseType = BCD)
    private String posCondCode;
    @IsoFieldMeta(domainNo = 26, fixed = true, length = 1, remark = "服务点PIN获取码", parseType = BCD)
    private String posPinCaptrCode;
    @IsoFieldMeta(domainNo = 28, fixed = true, length = 9, remark = "交易费", existSubdomain = true, subdomainType = FIXED)
    private Field28 amtTransFee;
    @IsoFieldMeta(domainNo = 32, fixed = false, length = 1, maxLength = 6, remark = "代理机构标识码", parseType = BCD, lenParseType = BINARY)
    private String acqInstIdCode;
    @IsoFieldMeta(domainNo = 33, fixed = false, length = 1, maxLength = 6, remark = "发送机构标识码", parseType = BCD, lenParseType = BINARY)
    private String fwdInstIdCode;
    @IsoFieldMeta(domainNo = 35, fixed = false, length = 1, maxLength = 20, remark = "第二磁道数据", parseType = BCD, status = RAW, lenParseType = BINARY)
    private byte[] tracK2Data;
    @IsoFieldMeta(domainNo = 36, fixed = false, length = 1, maxLength = 54, remark = "第三磁道数据", parseType = BCD, lenParseType = BINARY)
    private byte[] tracK3Data;
    @IsoFieldMeta(domainNo = 37, fixed = true, length = 12, remark = "检索参考号", parseType = EBCDIC)
    private String retrivlRefNum;
    @IsoFieldMeta(domainNo = 38, fixed = true, length = 6, remark = "授权标识应答码", parseType = EBCDIC)
    private String authrIdResp;
    @IsoFieldMeta(domainNo = 39, fixed = true, length = 2, remark = "应答码", parseType = EBCDIC)
    private String respCode;
    @IsoFieldMeta(domainNo = 41, fixed = true, length = 8, remark = "受卡机终端标识码", parseType = EBCDIC)
    private String cardAccptrTermnlId;
    @IsoFieldMeta(domainNo = 42, fixed = true, length = 15, remark = "受卡方标识码", parseType = EBCDIC)
    private String cardAccptrId;
    @IsoFieldMeta(domainNo = 43, fixed = true, length = 40, remark = "受卡方名称地址", existSubdomain = true, subdomainType = FIXED)
    private Field43 cardAccptrNameLoc;
    @IsoFieldMeta(domainNo = 44, fixed = false, length = 1, maxLength = 99, remark = "附加响应数据", existSubdomain = true, subdomainType = FIXED, lenParseType = BINARY)
    private Field44 addtnlRespCode;
    @IsoFieldMeta(domainNo = 45, fixed = false, length = 1, maxLength = 76, remark = "第一磁道数据", parseType = EBCDIC, lenParseType = BINARY)
    private String tracK1Data;
    @IsoFieldMeta(domainNo = 48, fixed = false, length = 1, maxLength = 255, remark = "附加数据——私有", existSubdomain = true, subdomainType = ULV2, lenParseType = BINARY, nonSubdomainException = false)
    private UsageSubdomain<Field48> addtnlDataPrivate;
    @IsoFieldMeta(domainNo = 49, fixed = true, length = 3, remark = "交易货币代码", parseType = EBCDIC)
    private String currcyCodeTrans;
    @IsoFieldMeta(domainNo = 50, fixed = true, length = 3, remark = "清算货币代码", parseType = EBCDIC)
    private String currcyCodeSettlmt;
    @IsoFieldMeta(domainNo = 51, fixed = true, length = 3, remark = "持卡人帐户货币代码", parseType = EBCDIC)
    private String currcyCodeCdhldrBil;
    @IsoFieldMeta(domainNo = 52, fixed = true, length = 8, remark = "个人标识码数据")
    private byte[] pinData;
    @IsoFieldMeta(domainNo = 53, fixed = true, length = 8, remark = "安全控制信息", existSubdomain = true, subdomainType = FIXED)
    private Field53 secRelatdCtrlInfo;
    @IsoFieldMeta(domainNo = 54, fixed = false, length = 1, maxLength = 120, remark = "实际余额", existSubdomain = true, subdomainType = FIXED, lenParseType = BINARY)
    private Field54 addtnlAmt;
    @IsoFieldMeta(domainNo = 55, fixed = false, length = 2, maxLength = 255, remark = "IC卡数据域", existSubdomain = true, subdomainType = TLV, lenParseType = BINARY)
    private Field55 iccData;
    @IsoFieldMeta(domainNo = 60, fixed = false, length = 1, maxLength = 255, remark = "STIP related information", existSubdomain = true, subdomainType = ULV2, lenParseType = BINARY)
    private UsageSubdomain<Field60> stipRelInfo;
    @IsoFieldMeta(domainNo = 61, fixed = false, length = 1, maxLength = 255, remark = "point of service information", existSubdomain = true, subdomainType = FIXED, lenParseType = BINARY)
    private Field61 ponitOfServiceInfo;
    @IsoFieldMeta(domainNo = 63, fixed = false, length = 1, maxLength = 255, remark = "transaction code", parseType = EBCDIC, lenParseType = BINARY)
    private String finaclNetData;
    @IsoFieldMeta(domainNo = 70, fixed = true, length = 2, remark = "网络管理信息码", parseType = BCD)
    private String netwkMgmtInfoCode;
    @IsoFieldMeta(domainNo = 90, fixed = true, length = 21, remark = "原始数据元", existSubdomain = true, subdomainType = FIXED)
    private Field90 origDataElemts;
    @IsoFieldMeta(domainNo = 95, fixed = true, length = 42, remark = "replacement amounts", existSubdomain = true, subdomainType = FIXED)
    private Field95 replacementAmt;
    @IsoFieldMeta(domainNo = 96, fixed = true, length = 8, remark = "报文安全码")
    private byte[] msgSecurityCode;
    @IsoFieldMeta(domainNo = 100, fixed = false, length = 1, maxLength = 6, remark = "接收机构标识码", parseType = BCD, lenParseType = BINARY)
    private String rcvgInstIdCode;
    @IsoFieldMeta(domainNo = 101, fixed = false, length = 1, maxLength = 17, remark = "file name", parseType = EBCDIC, lenParseType = BINARY)
    private String fileName;
    @IsoFieldMeta(domainNo = 105, fixed = true, length = 16, remark = "message security code for triple DES")
    private byte[] msgSecurityCodeForTriDes;
    @IsoFieldMeta(domainNo = 120, fixed = false, length = 2, maxLength = 30, remark = "record data", existSubdomain = true, subdomainType = FIXED, lenParseType = BINARY)
    private Field120 recordData;
    @IsoFieldMeta(domainNo = 127, fixed = false, length = 2, maxLength = 6, remark = "negative data access code", parseType = EBCDIC, lenParseType = BINARY)
    private String negDataAccessCode;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
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

    public String getSysTraceAuditNum() {
        return sysTraceAuditNum;
    }

    public void setSysTraceAuditNum(String sysTraceAuditNum) {
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

    public YearMonth getDateExpr() {
        return dateExpr;
    }

    public void setDateExpr(YearMonth dateExpr) {
        this.dateExpr = dateExpr;
    }

    public MonthDay getDateConv() {
        return dateConv;
    }

    public void setDateConv(MonthDay dateConv) {
        this.dateConv = dateConv;
    }

    public String getMchntType() {
        return mchntType;
    }

    public void setMchntType(String mchntType) {
        this.mchntType = mchntType;
    }

    public String getPosEntryModeCode() {
        return posEntryModeCode;
    }

    public void setPosEntryModeCode(String posEntryModeCode) {
        this.posEntryModeCode = posEntryModeCode;
    }

    public String getCardSeqId() {
        return cardSeqId;
    }

    public void setCardSeqId(String cardSeqId) {
        this.cardSeqId = cardSeqId;
    }

    public String getPosCondCode() {
        return posCondCode;
    }

    public void setPosCondCode(String posCondCode) {
        this.posCondCode = posCondCode;
    }

    public String getPosPinCaptrCode() {
        return posPinCaptrCode;
    }

    public void setPosPinCaptrCode(String posPinCaptrCode) {
        this.posPinCaptrCode = posPinCaptrCode;
    }

    public Field28 getAmtTransFee() {
        return amtTransFee;
    }

    public void setAmtTransFee(Field28 amtTransFee) {
        this.amtTransFee = amtTransFee;
    }

    public String getAcqInstIdCode() {
        return acqInstIdCode;
    }

    public void setAcqInstIdCode(String acqInstIdCode) {
        this.acqInstIdCode = acqInstIdCode;
    }

    public String getFwdInstIdCode() {
        return fwdInstIdCode;
    }

    public void setFwdInstIdCode(String fwdInstIdCode) {
        this.fwdInstIdCode = fwdInstIdCode;
    }

    public byte[] getTracK2Data() {
        return tracK2Data;
    }

    public void setTracK2Data(byte[] tracK2Data) {
        this.tracK2Data = tracK2Data;
    }

    public byte[] getTracK3Data() {
        return tracK3Data;
    }

    public void setTracK3Data(byte[] tracK3Data) {
        this.tracK3Data = tracK3Data;
    }

    public String getRetrivlRefNum() {
        return retrivlRefNum;
    }

    public void setRetrivlRefNum(String retrivlRefNum) {
        this.retrivlRefNum = retrivlRefNum;
    }

    public String getAuthrIdResp() {
        return authrIdResp;
    }

    public void setAuthrIdResp(String authrIdResp) {
        this.authrIdResp = authrIdResp;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getCardAccptrTermnlId() {
        return cardAccptrTermnlId;
    }

    public void setCardAccptrTermnlId(String cardAccptrTermnlId) {
        this.cardAccptrTermnlId = cardAccptrTermnlId;
    }

    public String getCardAccptrId() {
        return cardAccptrId;
    }

    public void setCardAccptrId(String cardAccptrId) {
        this.cardAccptrId = cardAccptrId;
    }

    public Field43 getCardAccptrNameLoc() {
        return cardAccptrNameLoc;
    }

    public void setCardAccptrNameLoc(Field43 cardAccptrNameLoc) {
        this.cardAccptrNameLoc = cardAccptrNameLoc;
    }

    public Field44 getAddtnlRespCode() {
        return addtnlRespCode;
    }

    public void setAddtnlRespCode(Field44 addtnlRespCode) {
        this.addtnlRespCode = addtnlRespCode;
    }

    public String getTracK1Data() {
        return tracK1Data;
    }

    public void setTracK1Data(String tracK1Data) {
        this.tracK1Data = tracK1Data;
    }

    public UsageSubdomain<Field48> getAddtnlDataPrivate() {
        return addtnlDataPrivate;
    }

    public void setAddtnlDataPrivate(UsageSubdomain<Field48> addtnlDataPrivate) {
        this.addtnlDataPrivate = addtnlDataPrivate;
    }

    public String getCurrcyCodeTrans() {
        return currcyCodeTrans;
    }

    public void setCurrcyCodeTrans(String currcyCodeTrans) {
        this.currcyCodeTrans = currcyCodeTrans;
    }

    public String getCurrcyCodeSettlmt() {
        return currcyCodeSettlmt;
    }

    public void setCurrcyCodeSettlmt(String currcyCodeSettlmt) {
        this.currcyCodeSettlmt = currcyCodeSettlmt;
    }

    public String getCurrcyCodeCdhldrBil() {
        return currcyCodeCdhldrBil;
    }

    public void setCurrcyCodeCdhldrBil(String currcyCodeCdhldrBil) {
        this.currcyCodeCdhldrBil = currcyCodeCdhldrBil;
    }

    public byte[] getPinData() {
        return pinData;
    }

    public void setPinData(byte[] pinData) {
        this.pinData = pinData;
    }

    public Field53 getSecRelatdCtrlInfo() {
        return secRelatdCtrlInfo;
    }

    public void setSecRelatdCtrlInfo(Field53 secRelatdCtrlInfo) {
        this.secRelatdCtrlInfo = secRelatdCtrlInfo;
    }

    public Field54 getAddtnlAmt() {
        return addtnlAmt;
    }

    public void setAddtnlAmt(Field54 addtnlAmt) {
        this.addtnlAmt = addtnlAmt;
    }

    public Field55 getIccData() {
        return iccData;
    }

    public void setIccData(Field55 iccData) {
        this.iccData = iccData;
    }

    public UsageSubdomain<Field60> getStipRelInfo() {
        return stipRelInfo;
    }

    public void setStipRelInfo(UsageSubdomain<Field60> stipRelInfo) {
        this.stipRelInfo = stipRelInfo;
    }

    public Field61 getPonitOfServiceInfo() {
        return ponitOfServiceInfo;
    }

    public void setPonitOfServiceInfo(Field61 ponitOfServiceInfo) {
        this.ponitOfServiceInfo = ponitOfServiceInfo;
    }

    public String getFinaclNetData() {
        return finaclNetData;
    }

    public void setFinaclNetData(String finaclNetData) {
        this.finaclNetData = finaclNetData;
    }

    public String getNetwkMgmtInfoCode() {
        return netwkMgmtInfoCode;
    }

    public void setNetwkMgmtInfoCode(String netwkMgmtInfoCode) {
        this.netwkMgmtInfoCode = netwkMgmtInfoCode;
    }

    public Field90 getOrigDataElemts() {
        return origDataElemts;
    }

    public void setOrigDataElemts(Field90 origDataElemts) {
        this.origDataElemts = origDataElemts;
    }

    public Field95 getReplacementAmt() {
        return replacementAmt;
    }

    public void setReplacementAmt(Field95 replacementAmt) {
        this.replacementAmt = replacementAmt;
    }

    public byte[] getMsgSecurityCode() {
        return msgSecurityCode;
    }

    public void setMsgSecurityCode(byte[] msgSecurityCode) {
        this.msgSecurityCode = msgSecurityCode;
    }

    public String getRcvgInstIdCode() {
        return rcvgInstIdCode;
    }

    public void setRcvgInstIdCode(String rcvgInstIdCode) {
        this.rcvgInstIdCode = rcvgInstIdCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getMsgSecurityCodeForTriDes() {
        return msgSecurityCodeForTriDes;
    }

    public void setMsgSecurityCodeForTriDes(byte[] msgSecurityCodeForTriDes) {
        this.msgSecurityCodeForTriDes = msgSecurityCodeForTriDes;
    }

    public Field120 getRecordData() {
        return recordData;
    }

    public void setRecordData(Field120 recordData) {
        this.recordData = recordData;
    }

    public String getNegDataAccessCode() {
        return negDataAccessCode;
    }

    public void setNegDataAccessCode(String negDataAccessCode) {
        this.negDataAccessCode = negDataAccessCode;
    }
}
