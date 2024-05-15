package com.alatka.messages.model.fixed.b9001;

import com.alatka.messages.annotation.FixedFieldMeta;

public class Header {

    @FixedFieldMeta(domainNo = 1, length = 4, remark = "交易代码")
    private String trxType;
    @FixedFieldMeta(domainNo = 2, length = 6, remark = "响应码")
    private String retCode;
    @FixedFieldMeta(domainNo = 3, length = 4, remark = "银行代号")
    private String bnkNbr;
    @FixedFieldMeta(domainNo = 5, length = 2, remark = "网点代号")
    private String source;
    @FixedFieldMeta(domainNo = 4, length = 6, remark = "交易来源")
    private String brnNo;
    @FixedFieldMeta(domainNo = 6, length = 6, remark = "操作员号")
    private String opeNo;
    @FixedFieldMeta(domainNo = 7, length = 6, remark = "流水号")
    private Integer seqNo;

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getBnkNbr() {
        return bnkNbr;
    }

    public void setBnkNbr(String bnkNbr) {
        this.bnkNbr = bnkNbr;
    }

    public String getBrnNo() {
        return brnNo;
    }

    public void setBrnNo(String brnNo) {
        this.brnNo = brnNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOpeNo() {
        return opeNo;
    }

    public void setOpeNo(String opeNo) {
        this.opeNo = opeNo;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

}
