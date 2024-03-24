package com.alatka.messages.template;

import com.alatka.messages.annotation.FixedFieldMeta;
import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "3006",
        kind = MessageDefinition.Kind.response,
        remark = "信用卡账单明细查询")
public class Fixed3006Res extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 9, length = 4, remark = "证件号码", pattern = "yyMM")
    private YearMonth tranYM;
    @FixedFieldMeta(domainNo = 10, length = 1, remark = "币种选项")
    private String option;
    @FixedFieldMeta(domainNo = 11, length = 2, remark = "本次返回交易条数")
    private Integer counts;
    @FixedFieldMeta(domainNo = 12, length = 1, remark = "翻页标志")
    private String rtnInd;
    @FixedFieldMeta(domainNo = 13, fixed = false, length = 141, remark = "集合", pageSizeName = "counts", existSubdomain = true, subdomainType = MessageDefinition.DomainType.LIST, parseType = FieldDefinition.ParseType.NONE)
    private List<Fixed3006Page> elements;

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr;
    }

    public YearMonth getTranYM() {
        return tranYM;
    }

    public void setTranYM(YearMonth tranYM) {
        this.tranYM = tranYM;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getRtnInd() {
        return rtnInd;
    }

    public void setRtnInd(String rtnInd) {
        this.rtnInd = rtnInd;
    }

    public List<Fixed3006Page> getElements() {
        return elements;
    }

    public void setElements(List<Fixed3006Page> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Fixed3006Res{" +
                "cardNbr='" + cardNbr + '\'' +
                ", tranYM=" + tranYM +
                ", option='" + option + '\'' +
                ", counts=" + counts +
                ", rtnInd='" + rtnInd + '\'' +
                ", elements=" + elements +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fixed3006Res)) return false;
        if (!super.equals(o)) return false;
        Fixed3006Res that = (Fixed3006Res) o;
        return Objects.equals(cardNbr, that.cardNbr) && Objects.equals(tranYM, that.tranYM) && Objects.equals(option, that.option) && Objects.equals(counts, that.counts) && Objects.equals(rtnInd, that.rtnInd) && Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNbr, tranYM, option, counts, rtnInd, elements);
    }
}
