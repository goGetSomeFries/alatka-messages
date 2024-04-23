package com.alatka.messages.model.iso.cups;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.annotation.MessageMeta;

import java.math.BigDecimal;

import static com.alatka.messages.context.MessageDefinition.Kind.subPayload;
import static com.alatka.messages.context.MessageDefinition.Type.iso;

@MessageMeta(type = iso, group = "cups", code = "common", kind = subPayload, domain = "F48", usage = "PZ", remark = "银联8583 48域usage=PZ")
public class Field48$PZ implements Field48 {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 2, remark = "用户号码类型")
    private String userCodeType;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 40, remark = "用户号码（支付项目）")
    private String userCode;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 4, remark = "用户号码地区编码")
    private String userCodeArea;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 4, remark = "用户号码附加地区编码")
    private String userCodeExtArea;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 1, remark = "支付方式标志")
    private String payTag;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 2, remark = "支付方式类型")
    private String payType;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 40, remark = "支付方式号码")
    private String payCode;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 2, remark = "委托关系限期")
    private String durationOfDelegated;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 12, remark = "最高限制金额")
    private BigDecimal highLimitAmt;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 12, remark = "最低限制金额")
    private BigDecimal lowLimitAmt;
    @IsoFieldMeta(domainNo = 11, fixed = true, length = 17, remark = "支付区间")
    private String payDateRange;
    @IsoFieldMeta(domainNo = 12, fixed = true, length = 22, remark = "保留使用")
    private String reversed;

    public String getUserCodeType() {
        return userCodeType;
    }

    public void setUserCodeType(String userCodeType) {
        this.userCodeType = userCodeType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCodeArea() {
        return userCodeArea;
    }

    public void setUserCodeArea(String userCodeArea) {
        this.userCodeArea = userCodeArea;
    }

    public String getUserCodeExtArea() {
        return userCodeExtArea;
    }

    public void setUserCodeExtArea(String userCodeExtArea) {
        this.userCodeExtArea = userCodeExtArea;
    }

    public String getPayTag() {
        return payTag;
    }

    public void setPayTag(String payTag) {
        this.payTag = payTag;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getDurationOfDelegated() {
        return durationOfDelegated;
    }

    public void setDurationOfDelegated(String durationOfDelegated) {
        this.durationOfDelegated = durationOfDelegated;
    }

    public BigDecimal getHighLimitAmt() {
        return highLimitAmt;
    }

    public void setHighLimitAmt(BigDecimal highLimitAmt) {
        this.highLimitAmt = highLimitAmt;
    }

    public BigDecimal getLowLimitAmt() {
        return lowLimitAmt;
    }

    public void setLowLimitAmt(BigDecimal lowLimitAmt) {
        this.lowLimitAmt = lowLimitAmt;
    }

    public String getPayDateRange() {
        return payDateRange;
    }

    public void setPayDateRange(String payDateRange) {
        this.payDateRange = payDateRange;
    }

    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }
}
