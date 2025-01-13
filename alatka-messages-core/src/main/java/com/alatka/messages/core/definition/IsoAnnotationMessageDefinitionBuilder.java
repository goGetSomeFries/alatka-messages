package com.alatka.messages.core.definition;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 8583注解报文定义解析器
 *
 * @author ybliu
 * @see AnnotationMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class IsoAnnotationMessageDefinitionBuilder extends AnnotationMessageDefinitionBuilder<IsoFieldDefinition> {

    public IsoAnnotationMessageDefinitionBuilder(String packageName) {
        super(packageName);
    }

    @Override
    protected Class<? extends Annotation> annotationClass() {
        return IsoFieldMeta.class;
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.iso;
    }

    @Override
    protected IsoFieldDefinition buildFieldDefinition(Field field) {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        IsoFieldMeta annotation = field.getAnnotation(IsoFieldMeta.class);
        fieldDefinition.setDomainNo(annotation.domainNo());
        fieldDefinition.setName(field.getName());
        fieldDefinition.setClassType(field.getType());
        fieldDefinition.setPattern(annotation.pattern());
        fieldDefinition.setFixed(annotation.fixed());
        fieldDefinition.setLength(annotation.length());
        fieldDefinition.setRemark(annotation.remark());
        fieldDefinition.setStatus(annotation.status());
        fieldDefinition.setParseType(annotation.parseType());
        fieldDefinition.setLenParseType(annotation.lenParseType());
        fieldDefinition.setExistSubdomain(annotation.existSubdomain());
        fieldDefinition.setSubdomainType(annotation.subdomainType());
        fieldDefinition.setPageSizeName(annotation.pageSizeName());

        fieldDefinition.setNonSubdomainException(annotation.nonSubdomainException());
        fieldDefinition.setAliasName(annotation.aliasName());
        int maxLength = annotation.maxLength();
        fieldDefinition.setMaxLength(fieldDefinition.getFixed() && maxLength == -1 ?
                fieldDefinition.getLength() : maxLength);
        return fieldDefinition;
    }
}
