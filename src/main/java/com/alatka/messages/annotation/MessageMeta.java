package com.alatka.messages.annotation;

import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.support.Constant;

import java.lang.annotation.*;

/**
 * 报文解析定义模板
 *
 * @author ybliu
 * @see FixedFieldMeta
 * @see IsoFieldMeta
 * @see MessageDefinition
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MessageMeta {

    /**
     * {@link MessageDefinition#setType(MessageDefinition.Type)}
     */
    MessageDefinition.Type type();

    /**
     * {@link MessageDefinition#setGroup(String)}
     */
    String group();

    /**
     * {@link MessageDefinition#setCode(String)}
     */
    String code();

    /**
     * {@link MessageDefinition#setDomain(String)}
     */
    String domain() default "";

    /**
     * {@link MessageDefinition#setUsage(String)}
     */
    String usage() default "";

    /**
     * {@link MessageDefinition.Kind}
     */
    MessageDefinition.Kind kind() default MessageDefinition.Kind.none;

    /**
     * {@link MessageDefinition.DomainType}
     */
    MessageDefinition.DomainType domainType() default MessageDefinition.DomainType.NONE;

    /**
     * {@link MessageDefinition#setHeader(MessageDefinition)}
     */
    Class<?> header() default Class.class;

    /**
     * true: 使用 {@link MessageHolder} 对象，作为报文解析后的容器<br>
     * false: 使用注解定义类的对象，作为报文解析后的容器
     * {@link MessageDefinition#setHolder(Class)}
     */
    boolean customize() default false;

    /**
     * {@link MessageDefinition#setCharset(String)}
     */
    String charset() default Constant.DEFAULT_CHARSET;

    /**
     * {@link MessageDefinition#setRemark(String)}
     */
    String remark() default "";
}
