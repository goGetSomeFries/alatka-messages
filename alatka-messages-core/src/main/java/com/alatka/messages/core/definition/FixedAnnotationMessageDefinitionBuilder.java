package com.alatka.messages.core.definition;

import com.alatka.messages.core.annotation.FixedFieldMeta;
import com.alatka.messages.core.context.FixedFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 固定格式注解报文定义解析器
 *
 * @author ybliu
 * @see AnnotationMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class FixedAnnotationMessageDefinitionBuilder extends AnnotationMessageDefinitionBuilder<FixedFieldDefinition> {

    public FixedAnnotationMessageDefinitionBuilder(String packageName) {
        super(packageName);
    }

    @Override
    protected Class<? extends Annotation> annotationClass() {
        return FixedFieldMeta.class;
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.fixed;
    }

    @Override
    protected FixedFieldDefinition buildFieldDefinition(Field field) {
        FixedFieldDefinition fieldDefinition = new FixedFieldDefinition();
        FixedFieldMeta annotation = field.getAnnotation(FixedFieldMeta.class);
        fieldDefinition.setDomainNo(annotation.domainNo());
        fieldDefinition.setName(field.getName());
        fieldDefinition.setClassType(field.getType());
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
