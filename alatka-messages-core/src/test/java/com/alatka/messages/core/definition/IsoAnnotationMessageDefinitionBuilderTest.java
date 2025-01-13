package com.alatka.messages.core.definition;

import com.alatka.messages.core.annotation.IsoFieldMeta;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class IsoAnnotationMessageDefinitionBuilderTest {

    private IsoAnnotationMessageDefinitionBuilder builder = new IsoAnnotationMessageDefinitionBuilder("");

    @Test
    @DisplayName("type()")
    void test01() {
        Assertions.assertEquals(MessageDefinition.Type.iso, builder.type());
    }

    @Test
    @DisplayName("annotationClass()")
    void test02() {
        Assertions.assertEquals(IsoFieldMeta.class, builder.annotationClass());
    }

    @Test
    @DisplayName("buildFieldDefinition()")
    void test03() {
        IsoFieldMeta annotation = Mockito.mock(IsoFieldMeta.class);
        Mockito.doReturn(1).when(annotation).domainNo();
        Mockito.doReturn("yyyyMMdd").when(annotation).pattern();
        Mockito.doReturn(true).when(annotation).fixed();
        Mockito.doReturn(20).when(annotation).length();
        Mockito.doReturn("testing").when(annotation).remark();
        Mockito.doReturn(FieldDefinition.Status.OPEN).when(annotation).status();
        Mockito.doReturn(FieldDefinition.ParseType.ASCII).when(annotation).parseType();
        Mockito.doReturn(FieldDefinition.ParseType.EBCDIC).when(annotation).lenParseType();
        Mockito.doReturn(false).when(annotation).existSubdomain();
        Mockito.doReturn(MessageDefinition.DomainType.FIXED).when(annotation).subdomainType();
        Mockito.doReturn("counts").when(annotation).pageSizeName();
        Mockito.doReturn(false).when(annotation).nonSubdomainException();
        Mockito.doReturn("alias").when(annotation).aliasName();
        Mockito.doReturn(-1).when(annotation).maxLength();


        Field field = Mockito.mock(Field.class);
        Mockito.doReturn("test").when(field).getName();
        Mockito.doReturn(String.class).when(field).getType();
        Mockito.doReturn(annotation).when(field).getAnnotation(Mockito.any());

        IsoFieldDefinition definition = builder.buildFieldDefinition(field);

        Assertions.assertEquals(1, definition.getDomainNo());
        Assertions.assertEquals("test", definition.getName());
        Assertions.assertEquals(String.class, definition.getClassType());
        Assertions.assertEquals("yyyyMMdd", definition.getPattern());
        Assertions.assertTrue(definition.getFixed());
        Assertions.assertEquals(20, definition.getLength());
        Assertions.assertEquals("testing", definition.getRemark());
        Assertions.assertEquals(FieldDefinition.Status.OPEN, definition.getStatus());
        Assertions.assertEquals(FieldDefinition.ParseType.ASCII, definition.getParseType());
        Assertions.assertEquals(FieldDefinition.ParseType.EBCDIC, definition.getLenParseType());
        Assertions.assertFalse(definition.getExistSubdomain());
        Assertions.assertEquals(MessageDefinition.DomainType.FIXED, definition.getSubdomainType());
        Assertions.assertEquals("counts", definition.getPageSizeName());
        Assertions.assertFalse(definition.getNonSubdomainException());
        Assertions.assertEquals("alias", definition.getAliasName());
        Assertions.assertEquals(20, definition.getMaxLength());
    }
}
