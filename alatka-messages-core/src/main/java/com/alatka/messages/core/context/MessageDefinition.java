package com.alatka.messages.core.context;

import com.alatka.messages.core.holder.MessageHolder;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 报文定义类
 *
 * @author ybliu
 */
public class MessageDefinition implements Comparable<MessageDefinition> {

    /**
     * 报文类型<br>
     * {@link Type#iso}
     * {@link Type#fixed}
     */
    private Type type;
    /**
     * 报文分组
     * 8583: visa/jcb/master/cups...<br>
     * fixed: 9001...
     */
    private String group;
    /**
     * 报文交易码
     */
    private String code;
    /**
     * 报文种类<br>
     * {@link Kind#header} {@link Kind#subPayload} ...
     */
    private Kind kind;
    /**
     * 子域名称
     */
    private String domain;
    /**
     * 8583使用标识
     */
    private String usage;
    /**
     * 报文域类型
     */
    private DomainType domainType;
    /**
     * 报文实体类<br>
     * {@link MessageHolder}/POJO
     */
    private Class<?> holder;
    /**
     * 报文编码
     */
    private String charset;
    /**
     * 报文描述
     */
    private String remark;

    public enum Type {
        /**
         * 8583
         */
        iso,
        /**
         * 固定格式
         */
        fixed
    }

    // enum顺序不可更改
    public enum Kind {
        /**
         * 报文头
         */
        header,
        /**
         * 子域
         */
        subPayload,
        /**
         * 报文体（8583/固定格式）
         */
        payload,
        /**
         * 报文请求体
         */
        request,
        /**
         * 报文应答体
         */
        response,
        /**
         * 其他
         */
        none
    }

    public enum DomainType {
        TLV, TLV2, TV, ULV, ULV2, UV, UVAS, PAGE, FIXED, BITMAP, NONE
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public DomainType getDomainType() {
        return domainType;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setDomainType(DomainType domainType) {
        this.domainType = domainType;
    }

    public Class<?> getHolder() {
        return holder;
    }

    public void setHolder(Class<?> holder) {
        this.holder = holder;
    }

    public Charset getCharset() {
        return Charset.forName(charset);
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

    public MessageDefinition copy() {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(this.type);
        definition.setGroup(this.group);
        definition.setCode(this.code);
        definition.setKind(this.kind);
        definition.setDomain(this.domain);
        definition.setUsage(this.usage);
        return definition;
    }

    public String identity() {
        return Stream.of(this.type.name(),
                        this.group,
                        this.code,
                        this.kind.name(),
                        this.domain,
                        this.usage)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(":"));
    }

    @Override
    public int compareTo(MessageDefinition definition) {
        int typeOrder = this.type.compareTo(definition.type);
        int groupOrder = this.group.compareTo(definition.group);
        int codeOrder = this.code.compareTo(definition.code);
        int kindOrder = this.kind.compareTo(definition.kind);
        int domainOrder = this.domain.compareTo(definition.domain);
        int usageOrder = this.usage.compareTo(definition.usage);
        return typeOrder != 0 ? typeOrder
                : groupOrder != 0 ? groupOrder
                : codeOrder != 0 ? codeOrder
                : kindOrder != 0 ? kindOrder
                : domainOrder != 0 ? domainOrder : usageOrder;
    }

    @Override
    public String toString() {
        String result = Stream.of(this.identity(), this.domainType, this.remark)
                .filter((Object s) -> s != null && !s.toString().isEmpty())
                .map(Object::toString)
                .collect(Collectors.joining(":"));
        return "MessageDefinition{" + result + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDefinition that = (MessageDefinition) o;
        return Objects.equals(type, that.type)
                && Objects.equals(group, that.group)
                && Objects.equals(code, that.code)
                && Objects.equals(kind, that.kind)
                && Objects.equals(domain, that.domain)
                && Objects.equals(usage, that.usage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, group, code, kind, domain, usage);
    }
}
