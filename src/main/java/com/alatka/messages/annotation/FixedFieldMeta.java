package com.alatka.messages.annotation;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.FixedFieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.lang.annotation.*;

/**
 * 固定格式报文域元配置
 *
 * @author ybliu
 * @see MessageMeta
 * @see FixedFieldDefinition
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface FixedFieldMeta {

    /**
     * {@link FixedFieldDefinition#setIndex(Integer)}
     */
    int domainNo();

    /**
     * {@link FixedFieldDefinition#setPattern(String)}
     */
    String pattern() default "";

    /**
     * {@link FixedFieldDefinition#setFixed(Boolean)}
     */
    boolean fixed() default true;

    /**
     * {@link FixedFieldDefinition#setLength(Integer)}
     */
    int length();

    /**
     * {@link FixedFieldDefinition#setExistSubdomain(Boolean)}
     */
    boolean existSubdomain() default false;

    /**
     * {@link FixedFieldDefinition#setSubdomainType(MessageDefinition.DomainType)}
     * {@link MessageDefinition.DomainType}
     */
    MessageDefinition.DomainType subdomainType() default MessageDefinition.DomainType.NONE;

    /**
     * {@link FixedFieldDefinition#setParseType(FieldDefinition.ParseType)}
     */
    FieldDefinition.ParseType parseType() default FieldDefinition.ParseType.ASCII;

    /**
     * {@link FixedFieldDefinition#setPageSizeName(String)}
     */
    String pageSizeName() default "";

    /**
     * {@link FixedFieldDefinition#setRemark(String)}
     */
    String remark() default "";

    /**
     * {@link IsoFieldDefinition#setStatus(FieldDefinition.Status)}
     */
    FieldDefinition.Status status() default FieldDefinition.Status.OPEN;
}
