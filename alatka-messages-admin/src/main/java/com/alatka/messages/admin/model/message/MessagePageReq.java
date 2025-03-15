package com.alatka.messages.admin.model.message;

import com.alatka.messages.admin.model.PageReqMessage;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "报文分页请求实体")
public class MessagePageReq extends PageReqMessage {

    @Schema(description = "类型")
    private String type;

    @Schema(description = "分组")
    private String group;

    @Schema(description = "交易码")
    private String code;

    @Schema(description = "种类")
    private String kind;

    @Schema(description = "子域名称")
    private String domain;

    @Schema(description = "8583报文子域usage")
    private String usage;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "是否可用")
    private Boolean enabled;

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
