package com.alatka.messages.core.message;

import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TLV2SubdomainMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new TLV2SubdomainMessageBuilder(definition));
    }

    @Test
    @DisplayName("doBuildFieldDefinitions()")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        TLV2SubdomainMessageBuilder messageBuilder = new TLV2SubdomainMessageBuilder(definition);

        byte[] bytes = "T01003ABAT03002B2".getBytes();
        List<String> list = messageBuilder.doBuildFieldDefinitions(bytes, null);
        String[] array = {"T01", "T03"};
        Assertions.assertEquals(array.length, list.size());
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], list.get(i));
        }
    }
}
