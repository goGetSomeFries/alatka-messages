package com.alatka.messages.core.domain.processor;

import com.alatka.messages.core.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RawDomainPostProcessorTest {

    private DomainPostProcessor processor = new RawDomainPostProcessor();

    @Test
    @DisplayName("name()")
    void test01() {
        Assertions.assertEquals("raw", processor.name());
    }

    @Test
    @DisplayName("postProcessAfterUnpacked()")
    void test03() {
        byte[] bytes = new byte[0];
        FieldDefinition definition = new FieldDefinition();
        Assertions.assertSame(bytes, processor.postProcessAfterUnpacked(bytes, definition));
    }

    @Test
    @DisplayName("postProcessAfterPacked()")
    void test04() {
        byte[] bytes = new byte[0];
        FieldDefinition definition = new FieldDefinition();
        Assertions.assertSame(bytes, processor.postProcessAfterPacked(bytes, definition));
    }
}
