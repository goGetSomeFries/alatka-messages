package com.alatka.messages.template;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.Arrays;
import java.util.Objects;

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.header,
        remark = "银联8583报文头")
public class IsoCupsHeader {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "头长度", parseType = FieldDefinition.ParseType.BINARY)
    private Integer headerLength;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "头标识和版本号", parseType = FieldDefinition.ParseType.BINARY)
    private String version;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 4, remark = "报文长度")
    private Integer messageLength;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 11, remark = "目的ID")
    private String destinationId;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 11, remark = "源ID")
    private String sourceId;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 3, remark = "保留使用", parseType = FieldDefinition.ParseType.NONE)
    private byte[] reserved;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "批次号", parseType = FieldDefinition.ParseType.BINARY)
    private String batchNum;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 8, remark = "交易信息")
    private String transInfo;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "用户信息", parseType = FieldDefinition.ParseType.BINARY)
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

    @Override
    public String toString() {
        return "IsoCupsHeader{" +
                "headerLength=" + headerLength +
                ", version='" + version + '\'' +
                ", messageLength=" + messageLength +
                ", destinationId='" + destinationId + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", reserved=" + Arrays.toString(reserved) +
                ", batchNum='" + batchNum + '\'' +
                ", transInfo='" + transInfo + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", rejectCode='" + rejectCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsoCupsHeader)) return false;
        IsoCupsHeader that = (IsoCupsHeader) o;
        return Objects.equals(headerLength, that.headerLength) && Objects.equals(version, that.version) && Objects.equals(messageLength, that.messageLength) && Objects.equals(destinationId, that.destinationId) && Objects.equals(sourceId, that.sourceId) && Arrays.equals(reserved, that.reserved) && Objects.equals(batchNum, that.batchNum) && Objects.equals(transInfo, that.transInfo) && Objects.equals(userInfo, that.userInfo) && Objects.equals(rejectCode, that.rejectCode);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(headerLength, version, messageLength, destinationId, sourceId, batchNum, transInfo, userInfo, rejectCode);
        result = 31 * result + Arrays.hashCode(reserved);
        return result;
    }
}
