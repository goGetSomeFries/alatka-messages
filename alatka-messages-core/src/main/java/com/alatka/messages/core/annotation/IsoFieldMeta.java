package com.alatka.messages.core.annotation;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.lang.annotation.*;

/**
 * 8583报文域元配置
 *
 * @author ybliu
 * @see MessageMeta
 * @see IsoFieldDefinition
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsoFieldMeta {

    /**
     * {@link IsoFieldDefinition#setDomainNo(Integer)}
     */
    int domainNo();

    /**
     * {@link IsoFieldDefinition#setPattern(String)}
     */
    String pattern() default "";

    /**
     * {@link IsoFieldDefinition#setFixed(Boolean)}
     */
    boolean fixed();

    /**
     * {@link IsoFieldDefinition#setLength(Integer)}
     */
    int length() default 0;

    /**
     * {@link IsoFieldDefinition#setMaxLength(Integer)}
     */
    int maxLength() default -1;

    /**
     * {@link IsoFieldDefinition#setExistSubdomain(Boolean)}
     */
    boolean existSubdomain() default false;

    /**
     * {@link IsoFieldDefinition#setSubdomainType(MessageDefinition.DomainType)}
     * {@link MessageDefinition.DomainType}
     */
    MessageDefinition.DomainType subdomainType() default MessageDefinition.DomainType.NONE;

    /**
     * {@link IsoFieldDefinition#setNonSubdomainException(Boolean)}
     */
    boolean nonSubdomainException() default true;

    /**
     * {@link IsoFieldDefinition#setParseType(FieldDefinition.ParseType)}
     */
    FieldDefinition.ParseType parseType() default FieldDefinition.ParseType.ASCII;

    /**
     * {@link IsoFieldDefinition#setParseType(FieldDefinition.ParseType)}
     */
    FieldDefinition.ParseType lenParseType() default FieldDefinition.ParseType.ASCII;

    /**
     * {@link IsoFieldDefinition#setPageSizeName(String)}
     */
    String pageSizeName() default "";

    /**
     * {@link IsoFieldDefinition#setAliasName(String)}
     */
    String aliasName() default "";

    /**
     * {@link IsoFieldDefinition#setRemark(String)}
     */
    String remark() default "";

    /**
     * {@link IsoFieldDefinition#setStatus(FieldDefinition.Status)}
     */
    FieldDefinition.Status status() default FieldDefinition.Status.OPEN;
}
