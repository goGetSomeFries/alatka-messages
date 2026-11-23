package com.alatka.messages.core.domain.processor;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class CompositeDomainPostProcessorTest {

    private CompositeDomainPostProcessor processor = new CompositeDomainPostProcessor();

    @Test
    @DisplayName("name()")
    void test01() {
        Assertions.assertEquals("composite", processor.name());
    }

    @Test
    @DisplayName("add()")
    void test02() {
        DomainPostProcessor processor1 = Mockito.mock(DomainPostProcessor.class);
        DomainPostProcessor processor2 = Mockito.mock(DomainPostProcessor.class);
        processor.add(processor1, processor2);

        List<DomainPostProcessor> processors = (List<DomainPostProcessor>) ClassUtil.getFieldValue(processor, "processors");
        Assertions.assertEquals(2, processors.size());
        Assertions.assertSame(processor1, processors.get(0));
        Assertions.assertSame(processor2, processors.get(1));
    }

    @Test
    @DisplayName("postProcessAfterUnpacked()")
    void test03() {
        FieldDefinition definition = new FieldDefinition();
        byte[] byte1 = new byte[0];
        byte[] byte2 = new byte[0];
        byte[] byte3 = new byte[0];
        DomainPostProcessor processor1 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn(byte2).when(processor1).postProcessAfterUnpacked(byte1, definition);
        DomainPostProcessor processor2 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn(byte3).when(processor2).postProcessAfterUnpacked(byte2, definition);

        processor.add(processor2, processor1);

        Assertions.assertSame(byte3, processor.postProcessAfterUnpacked(byte1, definition));
    }

    @Test
    @DisplayName("postProcessAfterPacked()")
    void test04() {
        FieldDefinition definition = new FieldDefinition();
        byte[] byte1 = new byte[0];
        byte[] byte2 = new byte[0];
        byte[] byte3 = new byte[0];
        DomainPostProcessor processor1 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn(byte2).when(processor1).postProcessAfterPacked(byte1, definition);
        DomainPostProcessor processor2 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn(byte3).when(processor2).postProcessAfterPacked(byte2, definition);

        processor.add(processor1, processor2);

        Assertions.assertSame(byte3, processor.postProcessAfterPacked(byte1, definition));
    }
}
