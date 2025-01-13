package com.alatka.messages.core.context;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Map;
import java.util.Objects;

/**
 * 报文域定义类
 *
 * @author ybliu
 * @see IsoFieldDefinition
 * @see FixedFieldDefinition
 */
public class FieldDefinition implements Comparable<FieldDefinition> {

    public static final String SUBFIELD_KEY_DEFAULT = "default";

    /**
     * 域序号
     */
    private Integer domainNo;
    /**
     * 域名称
     */
    private String name;
    /**
     * 域java类型
     */
    @JsonAlias({"clazz"})
    private String className;
    /**
     * 域java类型
     */
    private Class<?> classType;
    /**
     * 域值格式，日期类型使用
     */
    private String pattern;
    /**
     * 是否定长
     */
    private Boolean fixed;
    /**
     * 长度<br>
     * {@link IsoFieldDefinition#getFixed()} = true：字段字节长度<br>
     * {@link IsoFieldDefinition#getFixed()} = false：数据元字节长度
     */
    private Integer length;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     * {@link Status}
     */
    private Status status;
    /**
     * 分页记录数字段名称
     */
    private String pageSizeName;
    /**
     * 字段域解析类型
     */
    private ParseType parseType;
    /**
     * 是否存在子域
     */
    private Boolean existSubdomain = Boolean.FALSE;
    /**
     * 子域类型
     * {@link MessageDefinition.DomainType}
     */
    private MessageDefinition.DomainType subdomainType;
    /**
     * 使用标识映射{@link MessageDefinition}
     */
    private Map<String, MessageDefinition> messageDefinitionMap;

    public enum Status {
        /**
         * 正常
         */
        OPEN,
        /**
         * 关闭，不解析到{@link MessageDefinitionContext}中
         */
        CLOSE,
        /**
         * 报文域解析时不做处理
         */
        RAW
    }

    public enum ParseType {
        ASCII, EBCDIC, BCD, BINARY, NONE
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPageSizeName() {
        return pageSizeName;
    }

    public void setPageSizeName(String pageSizeName) {
        this.pageSizeName = pageSizeName;
    }

    public Boolean getExistSubdomain() {
        return existSubdomain;
    }

    public void setExistSubdomain(Boolean existSubdomain) {
        this.existSubdomain = existSubdomain;
    }

    public MessageDefinition.DomainType getSubdomainType() {
        return subdomainType;
    }

    public void setSubdomainType(MessageDefinition.DomainType subdomainType) {
        this.subdomainType = subdomainType;
    }

    public Map<String, MessageDefinition> getMessageDefinitionMap() {
        return messageDefinitionMap;
    }

    public void setMessageDefinitionMap(Map<String, MessageDefinition> messageDefinitionMap) {
        this.messageDefinitionMap = messageDefinitionMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldDefinition)) {
            return false;
        }
        FieldDefinition that = (FieldDefinition) o;
        return Objects.equals(domainNo, that.domainNo) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainNo, name);
    }

    @Override
    public int compareTo(FieldDefinition o) {
        return this.getDomainNo() - o.getDomainNo();
    }
}
