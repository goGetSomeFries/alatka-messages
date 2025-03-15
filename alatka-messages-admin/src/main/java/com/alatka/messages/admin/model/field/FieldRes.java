package com.alatka.messages.admin.model.field;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "报文域响应实体")
public class FieldRes {

    @Schema(description = "报文id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long messageId;

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "域序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer domainNo;

    @Schema(description = "域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "域别名")
    private String aliasName;

    @Schema(description = "是否定长", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean fixed;

    @Schema(description = "域字节长度", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer length;

    @Schema(description = "域最大长度", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer maxLength;

    @Schema(description = "域java类型")
    private String className;

    @Schema(description = "域值格式")
    private String pattern;

    @Schema(description = "域描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String remark;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "分页记录数字段名称")
    private String pageSizeName;

    @Schema(description = "域解析类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String parseType;

    @Schema(description = "变长域解析类型")
    private String lenParseType;

    @Schema(description = "是否存在子域", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean existSubdomain;

    @Schema(description = "子域类型")
    private String subdomainType;

    @Schema(description = "未配置子域异常")
    private Boolean nonSubdomainException;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public String getPageSizeName() {
        return pageSizeName;
    }

    public void setPageSizeName(String pageSizeName) {
        this.pageSizeName = pageSizeName;
    }

    public String getParseType() {
        return parseType;
    }

    public void setParseType(String parseType) {
        this.parseType = parseType;
    }

    public String getLenParseType() {
        return lenParseType;
    }

    public void setLenParseType(String lenParseType) {
        this.lenParseType = lenParseType;
    }

    public Boolean getExistSubdomain() {
        return existSubdomain;
    }

    public void setExistSubdomain(Boolean existSubdomain) {
        this.existSubdomain = existSubdomain;
    }

    public String getSubdomainType() {
        return subdomainType;
    }

    public void setSubdomainType(String subdomainType) {
        this.subdomainType = subdomainType;
    }

    public Boolean getNonSubdomainException() {
        return nonSubdomainException;
    }

    public void setNonSubdomainException(Boolean nonSubdomainException) {
        this.nonSubdomainException = nonSubdomainException;
    }
}
