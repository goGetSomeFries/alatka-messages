package com.alatka.messages.admin.model.message;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "报文请求实体")
public class MessageReq {

    @Schema(description = "报文域主键")
    private Long fieldId;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "type 不能为空")
    private String type;

    @Schema(description = "分组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "group 不能为空")
    private String group;

    @Schema(description = "交易码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "code 不能为空")
    private String code;

    @Schema(description = "种类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "kind 不能为空")
    private String kind;

    @Schema(description = "子域名称")
    private String domain;

    @Schema(description = "8583报文子域usage")
    private String usage;

    @Schema(description = "8583报文子域类型")
    private String domainType;

    @Schema(description = "实体类")
    private String holder;

    @Schema(description = "编码")
    private String charset;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "remark 不能为空")
    private String remark;

    @Schema(description = "是否可用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "enabled 不能为空")
    private Boolean enabled;

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
