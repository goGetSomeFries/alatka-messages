package com.alatka.messages.definition;

import com.alatka.messages.annotation.IsoFieldMeta;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.lang.reflect.Field;

/**
 * 8583注解报文定义解析器
 *
 * @author ybliu
 * @see AnnotationMessageDefinitionBuilder
 * @see AbstractMessageDefinitionBuilder
 */
public class IsoAnnotationMessageDefinitionBuilder extends AnnotationMessageDefinitionBuilder {

    public IsoAnnotationMessageDefinitionBuilder(String packageName) {
        super(packageName);
    }

    @Override
    protected boolean filter(Field field) {
        return field.isAnnotationPresent(IsoFieldMeta.class);
    }

    @Override
    protected MessageDefinition.Type type() {
        return MessageDefinition.Type.iso;
    }

    @Override
    protected IsoFieldDefinition buildFieldDefinition(Field field) {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        IsoFieldMeta annotation = field.getAnnotation(IsoFieldMeta.class);
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
        fieldDefinition.setNonSubdomainException(annotation.nonSubdomainException());
        fieldDefinition.setPageSizeName(annotation.pageSizeName());

        fieldDefinition.setAliasName(annotation.aliasName());
        fieldDefinition.setMaxLength(fieldDefinition.getFixed() && annotation.maxLength() == -1 ?
                fieldDefinition.getLength() : annotation.maxLength());
        return fieldDefinition;
    }
}
