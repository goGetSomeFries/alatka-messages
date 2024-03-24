package com.alatka.messages.template;

import com.alatka.messages.annotation.FixedFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.MessageDefinition;

import java.util.Objects;

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "4118",
        kind = MessageDefinition.Kind.response,
        remark = "客户以及账户标签内容交易")
public class Fixed4118Res extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 8, remark = "标志位")
    private String option;
    @FixedFieldMeta(domainNo = 9, length = 19, remark = "证件号码")
    private String custNbr;
    @FixedFieldMeta(domainNo = 10, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 11, length = 5, remark = "标签类型")
    private String tagType;
    @FixedFieldMeta(domainNo = 12, length = 4, remark = "标签代码")
    private String tagCode;
    @FixedFieldMeta(domainNo = 13, length = 20, remark = "标签内容")
    private String tagInfo;
    @FixedFieldMeta(domainNo = 14, length = 500, remark = "保留域")
    private String resvd;

    public String getOption() {
        return option;
    }

    public String getCustNbr() {
        return custNbr;
    }

    public void setCustNbr(String custNbr) {
        this.custNbr = custNbr;
    }

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(String tagInfo) {
        this.tagInfo = tagInfo;
    }

    public String getResvd() {
        return resvd;
    }

    public void setResvd(String resvd) {
        this.resvd = resvd;
    }

    public void setOption(String option) {
        this.option = option;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fixed4118Res)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Fixed4118Res that = (Fixed4118Res) o;
        return Objects.equals(option, that.option) &&
                Objects.equals(custNbr, that.custNbr) &&
                Objects.equals(cardNbr, that.cardNbr) &&
                Objects.equals(tagType, that.tagType) &&
                Objects.equals(tagCode, that.tagCode) &&
                Objects.equals(tagInfo, that.tagInfo) &&
                Objects.equals(resvd, that.resvd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), option, custNbr, cardNbr, tagType, tagCode, tagInfo, resvd);
    }
}