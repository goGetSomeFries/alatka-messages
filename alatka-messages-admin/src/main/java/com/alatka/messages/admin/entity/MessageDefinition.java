package com.alatka.messages.admin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ALK_MESSAGE_DEFINITION")
public class MessageDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    private Long id;

    @Column(name = "F_ID")
    private Long fieldId;

    @Column(name = "M_TYPE")
    private String type;

    @Column(name = "M_GROUP")
    private String group;

    @Column(name = "M_CODE")
    private String code;

    @Column(name = "M_KIND")
    private String kind;

    @Column(name = "M_DOMAIN")
    private String domain;

    @Column(name = "M_USAGE")
    private String usage;

    @Column(name = "M_DOMAIN_TYPE")
    private String domainType;

    @Column(name = "M_HOLDER")
    private String holder;

    @Column(name = "M_CHARSET")
    private String charset;

    @Column(name = "M_REMARK")
    private String remark;

    @Column(name = "M_ENABLED")
    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
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
