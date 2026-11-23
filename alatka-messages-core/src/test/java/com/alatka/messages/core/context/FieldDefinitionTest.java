package com.alatka.messages.core.context;

import com.alatka.messages.core.domain.processor.DomainPostProcessor;
import com.alatka.messages.core.domain.processor.DomainPostProcessorFactory;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FieldDefinitionTest {

    @Test
    @DisplayName("equals()")
    void test01() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setDomainNo(1);
        definition1.setName("test");
        FieldDefinition definition2 = new FieldDefinition();
        definition2.setDomainNo(1);
        definition2.setName("test");
        Assertions.assertEquals(definition1, definition1);
        Assertions.assertNotNull(definition1);
        Assertions.assertNotEquals("123", definition1);
        Assertions.assertEquals(definition1, definition2);
    }

    @Test
    @DisplayName("hashcode()")
    void test02() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setDomainNo(1);
        definition1.setName("test");

        int actual = Objects.hash(definition1.getDomainNo(), definition1.getName());
        Assertions.assertEquals(definition1.hashCode(), actual);
    }


    @Test
    @DisplayName("compareTo()")
    void test03() {
        FieldDefinition definition1 = new FieldDefinition();
        definition1.setDomainNo(3);
        FieldDefinition definition2 = new FieldDefinition();
        definition2.setDomainNo(1);
        int result = definition1.compareTo(definition2);
        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("getter() setter()")
    void test05() {
        FieldDefinition definition = new FieldDefinition();
        definition.setDomainNo(1);
        definition.setName("Name");
        definition.setClassName("java.lang.String");
        definition.setClassType(String.class);
        definition.setPattern("yyyyMMdd");
        definition.setFixed(true);
        definition.setLength(20);
        definition.setRemark("testing");
        definition.setStatus(FieldDefinition.Status.CLOSE);
        definition.setPageSizeName("counts");
        definition.setParseType(FieldDefinition.ParseType.NONE);
        definition.setExistSubdomain(Boolean.FALSE);
        definition.setSubdomainType(MessageDefinition.DomainType.PAGE);
        definition.setMessageDefinitionMap(Collections.EMPTY_MAP);

        DomainPostProcessor processor1 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn("testProcessor1").when(processor1).name();
        DomainPostProcessor processor2 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn("testProcessor2").when(processor2).name();
        DomainPostProcessorFactory.init(processor1);
        DomainPostProcessorFactory.init(processor2);

        definition.setProcessors("testProcessor1,testProcessor2");
        Assertions.assertEquals(1, definition.getDomainNo());
        Assertions.assertEquals("Name", definition.getName());
        Assertions.assertEquals("java.lang.String", definition.getClassName());
        Assertions.assertEquals(String.class, definition.getClassType());
        Assertions.assertEquals("yyyyMMdd", definition.getPattern());
        Assertions.assertEquals(true, definition.getFixed());
        Assertions.assertEquals(20, definition.getLength());
        Assertions.assertEquals("testing", definition.getRemark());
        Assertions.assertEquals(FieldDefinition.Status.CLOSE, definition.getStatus());
        Assertions.assertEquals("counts", definition.getPageSizeName());
        Assertions.assertEquals(FieldDefinition.ParseType.NONE, definition.getParseType());
        Assertions.assertEquals(Boolean.FALSE, definition.getExistSubdomain());
        Assertions.assertEquals(MessageDefinition.DomainType.PAGE, definition.getSubdomainType());
        Assertions.assertEquals(Collections.EMPTY_MAP, definition.getMessageDefinitionMap());

        List<DomainPostProcessor> processors = (List<DomainPostProcessor>) ClassUtil.getFieldValue(definition.getDomainPostProcessor(), "processors");
        Assertions.assertEquals(2, processors.size());
        Assertions.assertSame(processor1, processors.get(0));
        Assertions.assertSame(processor2, processors.get(1));
    }

    @Test
    @DisplayName("MessageDefinition.Status")
    void test06() {
        FieldDefinition.Status[] values = FieldDefinition.Status.values();
        String[] array = {"OPEN", "CLOSE", "RAW"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

    @Test
    @DisplayName("MessageDefinition.ParseType")
    void test07() {
        FieldDefinition.ParseType[] values = FieldDefinition.ParseType.values();
        String[] array = {"ASCII", "EBCDIC", "BCD", "BINARY", "NONE"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

}
