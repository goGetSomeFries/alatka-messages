package com.alatka.messages.core.definition;

import com.alatka.messages.core.annotation.FixedFieldMeta;
import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.FixedFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class FixedAnnotationMessageDefinitionBuilderTest {

    private FixedAnnotationMessageDefinitionBuilder builder = new FixedAnnotationMessageDefinitionBuilder("");

    @Test
    @DisplayName("type()")
    void test01() {
        Assertions.assertEquals(MessageDefinition.Type.fixed, builder.type());
    }

    @Test
    @DisplayName("annotationClass()")
    void test02() {
        Assertions.assertEquals(FixedFieldMeta.class, builder.annotationClass());
    }

    @Test
    @DisplayName("buildFieldDefinition()")
    void test03() {
        FixedFieldMeta annotation = Mockito.mock(FixedFieldMeta.class);
        Mockito.doReturn(1).when(annotation).domainNo();
        Mockito.doReturn("yyyyMMdd").when(annotation).pattern();
        Mockito.doReturn(false).when(annotation).fixed();
        Mockito.doReturn(20).when(annotation).length();
        Mockito.doReturn("testing").when(annotation).remark();
        Mockito.doReturn(FieldDefinition.Status.OPEN).when(annotation).status();
        Mockito.doReturn(FieldDefinition.ParseType.ASCII).when(annotation).parseType();
        Mockito.doReturn(false).when(annotation).existSubdomain();
        Mockito.doReturn(MessageDefinition.DomainType.FIXED).when(annotation).subdomainType();
        Mockito.doReturn("counts").when(annotation).pageSizeName();


        Field field = Mockito.mock(Field.class);
        Mockito.doReturn("test").when(field).getName();
        Mockito.doReturn(String.class).when(field).getType();
        Mockito.doReturn(annotation).when(field).getAnnotation(Mockito.any());

        FixedFieldDefinition definition = builder.buildFieldDefinition(field);

        Assertions.assertEquals(1, definition.getDomainNo());
        Assertions.assertEquals("test", definition.getName());
        Assertions.assertEquals(String.class, definition.getClassType());
        Assertions.assertEquals("yyyyMMdd", definition.getPattern());
        Assertions.assertFalse(definition.getFixed());
        Assertions.assertEquals(20, definition.getLength());
        Assertions.assertEquals("testing", definition.getRemark());
        Assertions.assertEquals(FieldDefinition.Status.OPEN, definition.getStatus());
        Assertions.assertEquals(FieldDefinition.ParseType.ASCII, definition.getParseType());
        Assertions.assertFalse(definition.getExistSubdomain());
        Assertions.assertEquals(MessageDefinition.DomainType.FIXED, definition.getSubdomainType());
        Assertions.assertEquals("counts", definition.getPageSizeName());

    }

    @Test
    @DisplayName("refresh()")
    void test04() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> builder.refresh(), "注解不支持动态加载");
    }
}
