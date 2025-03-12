package com.alatka.messages.admin.entity;

import javax.persistence.*;

@Entity
@Table(name = "ALK_FIELD_DEFINITION")
public class FieldDefinition {

    @Column(name = "M_ID")
    private Long messageId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_DOMAIN_NO")
    private Integer domainNo;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_ALIAS_NAME")
    private String aliasName;

    @Column(name = "F_FIXED")
    private Boolean fixed;

    @Column(name = "F_LENGTH")
    private Integer length;

    @Column(name = "F_MAX_LENGTH")
    private Integer maxLength;

    @Column(name = "F_CLASS_NAME")
    private String className;

    @Column(name = "F_PATTERN")
    private String pattern;

    @Column(name = "F_REMARK")
    private String remark;

    @Column(name = "F_STATUS")
    private String status;

    @Column(name = "F_PAGE_SIZE_NAME")
    private String pageSizeName;

    @Column(name = "F_PARSE_TYPE")
    private String parseType;

    @Column(name = "F_LEN_PARSE_TYPE")
    private String lenParseType;

    @Column(name = "F_EXIST_SUBDOMAIN")
    private Boolean existSubdomain;

    @Column(name = "F_SUBDOMAIN_TYPE")
    private String subdomainType;

    @Column(name = "F_NON_SUBDOMAIN_EXCEPTION")
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
