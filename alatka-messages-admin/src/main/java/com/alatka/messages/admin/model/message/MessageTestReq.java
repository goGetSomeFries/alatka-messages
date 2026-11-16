package com.alatka.messages.admin.model.message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "报文本地测试请求实体")
public class MessageTestReq {

    @Schema(description = "构建类型", required = true)
    @NotEmpty(message = "buildType 不能为空")
    private String buildType;

    @Schema(description = "构建参数")
    private String buildParam;

    @Schema(description = "MessageDefinition Identity", required = true)
    @NotEmpty(message = "identity 不能为空")
    private String identity;

    @Schema(description = "打包解包标识", required = true)
    @NotNull(message = "packed 不能为空")
    private Boolean packed;

    @Schema(description = "域名/名称解析标识", required = true)
    @NotNull(message = "byName 不能为空")
    private Boolean byName;

    @Schema(description = "结果详情标识", required = true)
    @NotNull(message = "detailed 不能为空")
    private Boolean detailed;

    @Schema(description = "报文", required = true)
    @NotEmpty(message = "content 不能为空")
    private String content;

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getBuildParam() {
        return buildParam;
    }

    public void setBuildParam(String buildParam) {
        this.buildParam = buildParam;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Boolean getPacked() {
        return packed;
    }

    public void setPacked(Boolean packed) {
        this.packed = packed;
    }

    public Boolean getByName() {
        return byName;
    }

    public void setByName(Boolean byName) {
        this.byName = byName;
    }

    public Boolean getDetailed() {
        return detailed;
    }

    public void setDetailed(Boolean detailed) {
        this.detailed = detailed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
