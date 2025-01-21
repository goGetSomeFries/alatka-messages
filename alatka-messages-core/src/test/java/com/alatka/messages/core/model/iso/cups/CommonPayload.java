package com.alatka.messages.core.model.iso.cups;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.holder.Bitmap;
import com.alatka.messages.core.holder.UsageSubdomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;

import static com.alatka.messages.core.context.FieldDefinition.ParseType.BINARY;
import static com.alatka.messages.core.context.MessageDefinition.DomainType.*;
import static com.alatka.messages.core.context.MessageDefinition.Kind.payload;
import static com.alatka.messages.core.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = payload, remark = "银联8583报文体")
public class CommonPayload {
    @IsoFieldMeta(domainNo = 0, fixed = true, length = 4, remark = "报文类型")
    private String messageType;
    @IsoFieldMeta(domainNo = 1, fixed = false, length = 16, remark = "位图", parseType = BINARY)
    private Bitmap bitMap;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = 2, maxLength = 19, remark = "主账号")
    private String pan;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 6, remark = "交易处理码")
    private String processingCode;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 12, remark = "交易金额")
    private BigDecimal amtTrans;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 12, remark = "清算金额")
    private BigDecimal amtSettlmt;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 12, remark = "持卡人扣账金额")
    private BigDecimal amtCdhldrBil;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 10, remark = "交易传输时间", pattern = "MMddHHmmss")
    LocalDateTime transmsnDateTime;
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
    @IsoFieldMeta(domainNo = 14, fixed = true, length = 4, remark = "卡有效期", pattern = "yyMM")
    private YearMonth dateExpr;
    @IsoFieldMeta(domainNo = 15, fixed = true, length = 4, remark = "清算日期", pattern = "MMdd")
    private MonthDay dateSettlmt;
    @IsoFieldMeta(domainNo = 16, fixed = true, length = 4, remark = "兑换日期", pattern = "MMdd")
    private MonthDay dateConv;
    @IsoFieldMeta(domainNo = 18, fixed = true, length = 4, remark = "商户类型")
    private String mchntType;
    @IsoFieldMeta(domainNo = 19, fixed = true, length = 3, remark = "商户国家代码")
    private String mchntCntryCode;
    @IsoFieldMeta(domainNo = 22, fixed = true, length = 3, remark = "服务点输入方式码")
    private String posEntryModeCode;
    @IsoFieldMeta(domainNo = 23, fixed = true, length = 3, remark = "卡序列号")
    private String cardSeqId;
    @IsoFieldMeta(domainNo = 25, fixed = true, length = 2, remark = "服务点条件码")
    private String posCondCode;
    @IsoFieldMeta(domainNo = 26, fixed = true, length = 2, remark = "服务点PIN获取码")
    private String posPinCaptrCode;
    @IsoFieldMeta(domainNo = 28, fixed = true, length = 9, remark = "交易费")
    private String amtTransFee;
    @IsoFieldMeta(domainNo = 32, fixed = false, length = 2, maxLength = 11, remark = "代理机构标识码")
    private String acqInstIdCode;
    @IsoFieldMeta(domainNo = 33, fixed = false, length = 2, maxLength = 11, remark = "发送机构标识码")
    private String fwdInstIdCode;
    @IsoFieldMeta(domainNo = 35, fixed = false, length = 2, maxLength = 37, remark = "第二磁道数据")
    private String tracK2Data;
    @IsoFieldMeta(domainNo = 36, fixed = false, length = 3, maxLength = 104, remark = "第三磁道数据")
    private String tracK3Data;
    @IsoFieldMeta(domainNo = 37, fixed = true, length = 12, remark = "检索参考号")
    private String retrivlRefNum;
    @IsoFieldMeta(domainNo = 38, fixed = true, length = 6, remark = "授权标识应答码")
    private String authrIdResp;
    @IsoFieldMeta(domainNo = 39, fixed = true, length = 2, remark = "应答码")
    private String respCode;
    @IsoFieldMeta(domainNo = 41, fixed = true, length = 8, remark = "受卡机终端标识码")
    private String cardAccptrTermnlId;
    @IsoFieldMeta(domainNo = 42, fixed = true, length = 15, remark = "受卡方标识码")
    private String cardAccptrId;
    @IsoFieldMeta(domainNo = 43, fixed = true, length = 40, remark = "受卡方名称地址")
    private String cardAccptrNameLoc;
    @IsoFieldMeta(domainNo = 44, fixed = false, length = 2, maxLength = 25, remark = "附加响应数据")
    private String addtnlRespCode;
    @IsoFieldMeta(domainNo = 45, fixed = false, length = 2, maxLength = 76, remark = "第一磁道数据")
    private String tracK1Data;
    @IsoFieldMeta(domainNo = 48, fixed = false, length = 3, maxLength = 512, remark = "附加数据——私有", existSubdomain = true, subdomainType = UVAS)
    private UsageSubdomain<Field48> addtnlDataPrivate;
    @IsoFieldMeta(domainNo = 49, fixed = true, length = 3, remark = "交易货币代码")
    private String currcyCodeTrans;
    @IsoFieldMeta(domainNo = 50, fixed = true, length = 3, remark = "清算货币代码")
    private String currcyCodeSettlmt;
    @IsoFieldMeta(domainNo = 51, fixed = true, length = 3, remark = "持卡人帐户货币代码")
    private String currcyCodeCdhldrBil;
    @IsoFieldMeta(domainNo = 52, fixed = true, length = 8, remark = "个人标识码数据")
    private byte[] pinData;
    @IsoFieldMeta(domainNo = 53, fixed = true, length = 16, remark = "安全控制信息", existSubdomain = true, subdomainType = FIXED)
    private Field53 secRelatdCtrlInfo;
    @IsoFieldMeta(domainNo = 54, fixed = false, length = 3, maxLength = 40, remark = "实际余额", existSubdomain = true, subdomainType = FIXED)
    private Field54 addtnlAmt;
    @IsoFieldMeta(domainNo = 55, fixed = false, length = 3, maxLength = 255, remark = "IC卡数据域", existSubdomain = true, subdomainType = TLV)
    private Field55 iccData;
    @IsoFieldMeta(domainNo = 56, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field56> addtnlData56;
    @IsoFieldMeta(domainNo = 57, fixed = false, length = 3, maxLength = 100, remark = "附加交易信息", existSubdomain = true, subdomainType = UVAS)
    private UsageSubdomain<Field57> addtnlData57;
    @IsoFieldMeta(domainNo = 59, fixed = false, length = 3, maxLength = 600, remark = "明细查询数据", existSubdomain = true, subdomainType = UV)
    private UsageSubdomain<Field59> detailInqrng;
    @IsoFieldMeta(domainNo = 60, fixed = false, length = 3, maxLength = 100, remark = "自定义域", existSubdomain = true, subdomainType = FIXED)
    private Field60 reserved;
    @IsoFieldMeta(domainNo = 61, fixed = false, length = 3, maxLength = 200, remark = "持卡人身份认证信息", existSubdomain = true, subdomainType = FIXED)
    private Field61 chAuthInfo;
    @IsoFieldMeta(domainNo = 62, fixed = false, length = 3, maxLength = 200, remark = "交换中心数据", existSubdomain = true, subdomainType = UV)
    private UsageSubdomain<Field62> switchingData;
    @IsoFieldMeta(domainNo = 63, fixed = false, length = 3, maxLength = 512, remark = "金融网络数据", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field63> finaclNetData;
    @IsoFieldMeta(domainNo = 70, fixed = true, length = 3, remark = "网络管理信息码")
    private String netwkMgmtInfoCode;
    @IsoFieldMeta(domainNo = 90, fixed = true, length = 42, remark = "原始数据元", existSubdomain = true, subdomainType = FIXED)
    private Field90 origDataElemts;
    @IsoFieldMeta(domainNo = 96, fixed = true, length = 8, remark = "报文安全码")
    private byte[] msgSecurityCode;
    @IsoFieldMeta(domainNo = 100, fixed = false, length = 2, maxLength = 11, remark = "接收机构标识码")
    private String rcvgInstIdCode;
    @IsoFieldMeta(domainNo = 102, fixed = false, length = 2, maxLength = 28, remark = "帐户标识1")
    private String acctId1;
    @IsoFieldMeta(domainNo = 103, fixed = false, length = 2, maxLength = 28, remark = "帐户标识2")
    private String acctId2;
    @IsoFieldMeta(domainNo = 104, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field104> addtnlData104;
    @IsoFieldMeta(domainNo = 113, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field113> addtnlData113;
    @IsoFieldMeta(domainNo = 116, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field116> addtnlData116;
    @IsoFieldMeta(domainNo = 117, fixed = false, length = 3, maxLength = 256, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field117> addtnlData117;
    @IsoFieldMeta(domainNo = 121, fixed = false, length = 3, maxLength = 100, remark = "银联处理中心保留", existSubdomain = true, subdomainType = FIXED)
    private Field121 nationalSwResved;
    @IsoFieldMeta(domainNo = 122, fixed = false, length = 3, maxLength = 100, remark = "受理方保留", existSubdomain = true, subdomainType = FIXED)
    private Field122 acqInstResvd;
    @IsoFieldMeta(domainNo = 123, fixed = false, length = 3, maxLength = 100, remark = "发卡方保留")
    private byte[] issrInstResvd;
    @IsoFieldMeta(domainNo = 125, fixed = false, length = 3, maxLength = 256, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field125> addtnlData125;
    @IsoFieldMeta(domainNo = 126, fixed = false, length = 3, maxLength = 256, remark = "附加信息", existSubdomain = true, subdomainType = ULV)
    private UsageSubdomain<Field126> addtnlData126;
    @IsoFieldMeta(domainNo = 128, fixed = true, length = 8, remark = "报文鉴别码")
    private String msgAuthnCode;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Bitmap getBitMap() {
        return bitMap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitMap = bitMap;
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

    public YearMonth getDateExpr() {
        return dateExpr;
    }

    public void setDateExpr(YearMonth dateExpr) {
        this.dateExpr = dateExpr;
    }

    public MonthDay getDateSettlmt() {
        return dateSettlmt;
    }

    public void setDateSettlmt(MonthDay dateSettlmt) {
        this.dateSettlmt = dateSettlmt;
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

    public String getMchntCntryCode() {
        return mchntCntryCode;
    }

    public void setMchntCntryCode(String mchntCntryCode) {
        this.mchntCntryCode = mchntCntryCode;
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

    public String getAmtTransFee() {
        return amtTransFee;
    }

    public void setAmtTransFee(String amtTransFee) {
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

    public String getTracK2Data() {
        return tracK2Data;
    }

    public void setTracK2Data(String tracK2Data) {
        this.tracK2Data = tracK2Data;
    }

    public String getTracK3Data() {
        return tracK3Data;
    }

    public void setTracK3Data(String tracK3Data) {
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

    public String getCardAccptrNameLoc() {
        return cardAccptrNameLoc;
    }

    public void setCardAccptrNameLoc(String cardAccptrNameLoc) {
        this.cardAccptrNameLoc = cardAccptrNameLoc;
    }

    public String getAddtnlRespCode() {
        return addtnlRespCode;
    }

    public void setAddtnlRespCode(String addtnlRespCode) {
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

    public UsageSubdomain<Field56> getAddtnlData56() {
        return addtnlData56;
    }

    public void setAddtnlData56(UsageSubdomain<Field56> addtnlData56) {
        this.addtnlData56 = addtnlData56;
    }

    public UsageSubdomain<Field57> getAddtnlData57() {
        return addtnlData57;
    }

    public void setAddtnlData57(UsageSubdomain<Field57> addtnlData57) {
        this.addtnlData57 = addtnlData57;
    }

    public UsageSubdomain<Field59> getDetailInqrng() {
        return detailInqrng;
    }

    public void setDetailInqrng(UsageSubdomain<Field59> detailInqrng) {
        this.detailInqrng = detailInqrng;
    }

    public Field60 getReserved() {
        return reserved;
    }

    public void setReserved(Field60 reserved) {
        this.reserved = reserved;
    }

    public Field61 getChAuthInfo() {
        return chAuthInfo;
    }

    public void setChAuthInfo(Field61 chAuthInfo) {
        this.chAuthInfo = chAuthInfo;
    }

    public UsageSubdomain<Field62> getSwitchingData() {
        return switchingData;
    }

    public void setSwitchingData(UsageSubdomain<Field62> switchingData) {
        this.switchingData = switchingData;
    }

    public UsageSubdomain<Field63> getFinaclNetData() {
        return finaclNetData;
    }

    public void setFinaclNetData(UsageSubdomain<Field63> finaclNetData) {
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

    public String getAcctId1() {
        return acctId1;
    }

    public void setAcctId1(String acctId1) {
        this.acctId1 = acctId1;
    }

    public String getAcctId2() {
        return acctId2;
    }

    public void setAcctId2(String acctId2) {
        this.acctId2 = acctId2;
    }

    public UsageSubdomain<Field104> getAddtnlData104() {
        return addtnlData104;
    }

    public void setAddtnlData104(UsageSubdomain<Field104> addtnlData104) {
        this.addtnlData104 = addtnlData104;
    }

    public UsageSubdomain<Field113> getAddtnlData113() {
        return addtnlData113;
    }

    public void setAddtnlData113(UsageSubdomain<Field113> addtnlData113) {
        this.addtnlData113 = addtnlData113;
    }

    public UsageSubdomain<Field116> getAddtnlData116() {
        return addtnlData116;
    }

    public void setAddtnlData116(UsageSubdomain<Field116> addtnlData116) {
        this.addtnlData116 = addtnlData116;
    }

    public UsageSubdomain<Field117> getAddtnlData117() {
        return addtnlData117;
    }

    public void setAddtnlData117(UsageSubdomain<Field117> addtnlData117) {
        this.addtnlData117 = addtnlData117;
    }

    public Field121 getNationalSwResved() {
        return nationalSwResved;
    }

    public void setNationalSwResved(Field121 nationalSwResved) {
        this.nationalSwResved = nationalSwResved;
    }

    public Field122 getAcqInstResvd() {
        return acqInstResvd;
    }

    public void setAcqInstResvd(Field122 acqInstResvd) {
        this.acqInstResvd = acqInstResvd;
    }

    public byte[] getIssrInstResvd() {
        return issrInstResvd;
    }

    public void setIssrInstResvd(byte[] issrInstResvd) {
        this.issrInstResvd = issrInstResvd;
    }

    public UsageSubdomain<Field125> getAddtnlData125() {
        return addtnlData125;
    }

    public void setAddtnlData125(UsageSubdomain<Field125> addtnlData125) {
        this.addtnlData125 = addtnlData125;
    }

    public UsageSubdomain<Field126> getAddtnlData126() {
        return addtnlData126;
    }

    public void setAddtnlData126(UsageSubdomain<Field126> addtnlData126) {
        this.addtnlData126 = addtnlData126;
    }

    public String getMsgAuthnCode() {
        return msgAuthnCode;
    }

    public void setMsgAuthnCode(String msgAuthnCode) {
        this.msgAuthnCode = msgAuthnCode;
    }
}