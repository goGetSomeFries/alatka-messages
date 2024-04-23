package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import static com.alatka.messages.context.FieldDefinition.ParseType.BINARY;
import static com.alatka.messages.context.MessageDefinition.Kind.header;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = header, remark = "银联8583报文头")
public class CommonHeader {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "头长度", parseType = BINARY)
    private Integer headerLength;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "头标识和版本号", parseType = BINARY)
    private String version;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 4, remark = "报文长度")
    private Integer messageLength;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 11, remark = "目的ID")
    private String destinationId;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 11, remark = "源ID")
    private String sourceId;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 3, remark = "保留使用")
    private byte[] reserved;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "批次号", parseType = BINARY)
    private String batchNum;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 8, remark = "交易信息")
    private String transInfo;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "用户信息", parseType = BINARY)
    private String userInfo;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 5, remark = "拒绝码")
    private String rejectCode;

    public Integer getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(Integer headerLength) {
        this.headerLength = headerLength;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(Integer messageLength) {
        this.messageLength = messageLength;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public byte[] getReserved() {
        return reserved;
    }

    public void setReserved(byte[] reserved) {
        this.reserved = reserved;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(String transInfo) {
        this.transInfo = transInfo;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

}
