package com.alatka.messages.core.message;

import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVSubdomainMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new TVSubdomainMessageBuilder(definition));
    }

    @Test
    @DisplayName("doBuildFieldDefinitions()")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        TVSubdomainMessageBuilder messageBuilder = new TVSubdomainMessageBuilder(definition);

        IsoFieldDefinition fieldDefinition1 = new IsoFieldDefinition();
        fieldDefinition1.setLength(3);
        IsoFieldDefinition fieldDefinition2 = new IsoFieldDefinition();
        fieldDefinition2.setLength(5);
        IsoFieldDefinition fieldDefinition3 = new IsoFieldDefinition();
        fieldDefinition3.setLength(2);
        Map<String, IsoFieldDefinition> tagMap = new HashMap<>();
        tagMap.put("T01", fieldDefinition1);
        tagMap.put("T02", fieldDefinition2);
        tagMap.put("T03", fieldDefinition3);
        byte[] bytes = "T01ABAT03B2".getBytes();
        List<String> list = messageBuilder.doBuildFieldDefinitions(bytes, tagMap);
        String[] array = {"T01", "T03"};
        Assertions.assertEquals(array.length, list.size());
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], list.get(i));
        }
    }
}
