package com.alatka.messages.definition;

import com.alatka.messages.annotation.FixedFieldMeta;
import com.alatka.messages.context.FixedFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.lang.reflect.Field;

/**
 * 固定格式注解报文定义解析器
 *
 * @author ybliu
 * @see AnnotationMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class FixedAnnotationMessageDefinitionBuilder extends AnnotationMessageDefinitionBuilder {

    public FixedAnnotationMessageDefinitionBuilder(String packageName) {
        super(packageName);
    }

    @Override
    protected boolean filter(Field field) {
        return field.isAnnotationPresent(FixedFieldMeta.class);
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.fixed;
    }

    @Override
    protected FixedFieldDefinition buildFieldDefinition(Field field) {
        FixedFieldDefinition fieldDefinition = new FixedFieldDefinition();
        FixedFieldMeta annotation = field.getAnnotation(FixedFieldMeta.class);
        fieldDefinition.setIndex(Integer.MIN_VALUE == annotation.index() ? annotation.domainNo() : annotation.index());
        fieldDefinition.setDomainNo(annotation.domainNo());
        fieldDefinition.setName(field.getName());
        fieldDefinition.setOriginClass(field.getType());
        fieldDefinition.setPattern(annotation.pattern());
        fieldDefinition.setFixed(annotation.fixed());
        fieldDefinition.setLength(annotation.length());
        fieldDefinition.setRemark(annotation.remark());
        fieldDefinition.setStatus(annotation.status());
        fieldDefinition.setParseType(annotation.parseType());
        fieldDefinition.setExistSubdomain(annotation.existSubdomain());
        fieldDefinition.setSubdomainType(annotation.subdomainType());
        fieldDefinition.setPageSizeName(annotation.pageSizeName());
        return fieldDefinition;
    }
}
