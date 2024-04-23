package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.time.LocalDateTime;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F90", remark = "银联8583 90域")
public class Field90 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 4, remark = "原始交易的报文类型")
    private String messageType;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 6, remark = "原始系统跟踪号")
    private Integer sysTraceAuditNum;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 10, remark = "原始交易传输时间", pattern = "MMddHHmmss")
    private LocalDateTime transmsnDateTime;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 11, remark = "代理机构标识码")
    private String acqInstIdCode;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 11, remark = "发送机构标识码")
    private String fwdInstIdCode;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getSysTraceAuditNum() {
        return sysTraceAuditNum;
    }

    public void setSysTraceAuditNum(Integer sysTraceAuditNum) {
        this.sysTraceAuditNum = sysTraceAuditNum;
    }

    public LocalDateTime getTransmsnDateTime() {
        return transmsnDateTime;
    }

    public void setTransmsnDateTime(LocalDateTime transmsnDateTime) {
        this.transmsnDateTime = transmsnDateTime;
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
}
