package com.alatka.messages.core.domain.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DomainPostProcessorFactoryTest {

    @Test
    void test01() {
        DomainPostProcessor processor1 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn("testProcessor1").when(processor1).name();
        DomainPostProcessor processor2 = Mockito.mock(DomainPostProcessor.class);
        Mockito.doReturn("testProcessor2").when(processor2).name();
        DomainPostProcessorFactory.init(processor1);
        DomainPostProcessorFactory.init(processor2);

        Assertions.assertSame(processor1, DomainPostProcessorFactory.getDomainPostProcessor("testProcessor1"));
        Assertions.assertSame(processor2, DomainPostProcessorFactory.getDomainPostProcessor("testProcessor2"));
    }
}
