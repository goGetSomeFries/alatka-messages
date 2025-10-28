package com.alatka.messages.admin.model.field;

import com.alatka.messages.admin.model.PageReqMessage;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "报文域分页请求实体")
public class FieldPageReq extends PageReqMessage {

    @Schema(description = "报文id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "messageId 不能为空")
    private Long messageId;

    @Schema(description = "域序号")
    private Integer domainNo;

    @Schema(description = "域名称")
    private String name;

    @Schema(description = "域描述")
    private String remark;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "是否存在子域")
    private Boolean existSubdomain;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getDomainNo() {
        return domainNo;
    }

    public void setDomainNo(Integer domainNo) {
        this.domainNo = domainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getExistSubdomain() {
        return existSubdomain;
    }

    public void setExistSubdomain(Boolean existSubdomain) {
        this.existSubdomain = existSubdomain;
    }
}
