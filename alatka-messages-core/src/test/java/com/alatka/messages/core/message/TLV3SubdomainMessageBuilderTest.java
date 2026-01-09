package com.alatka.messages.core.message;

import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TLV3SubdomainMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new TLV3SubdomainMessageBuilder(definition));
    }

    @Test
    @DisplayName("doBuildFieldDefinitions()")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        TLV3SubdomainMessageBuilder messageBuilder = new TLV3SubdomainMessageBuilder(definition);

        byte[] bytes = BytesUtil.toEBCDIC("001003ABA003002B2");
        List<String> list = messageBuilder.doBuildFieldDefinitions(bytes, null);
        String[] array = {"001", "003"};
        Assertions.assertEquals(array.length, list.size());
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], list.get(i));
        }
    }

    @Test
    @DisplayName("tagLength()")
    void test03() {
        MessageDefinition definition = new MessageDefinition();
        TLV3SubdomainMessageBuilder messageBuilder = new TLV3SubdomainMessageBuilder(definition);
        Assertions.assertEquals(3, messageBuilder.tagLength());
    }

    @Test
    @DisplayName("lenLength()")
    void test04() {
        MessageDefinition definition = new MessageDefinition();
        TLV3SubdomainMessageBuilder messageBuilder = new TLV3SubdomainMessageBuilder(definition);
        Assertions.assertEquals(3, messageBuilder.lenLength());
    }

    @Test
    @DisplayName("buildTag()")
    void test05() {
        MessageDefinition definition = new MessageDefinition();
        TLV3SubdomainMessageBuilder messageBuilder = new TLV3SubdomainMessageBuilder(definition);
        Assertions.assertEquals("001", messageBuilder.buildTag(BytesUtil.hexToBytes("F0F0F1")));
    }

    @Test
    @DisplayName("buildLen()")
    void test06() {
        MessageDefinition definition = new MessageDefinition();
        TLV3SubdomainMessageBuilder messageBuilder = new TLV3SubdomainMessageBuilder(definition);
        Assertions.assertEquals(2, messageBuilder.buildLen(BytesUtil.hexToBytes("F0F0F2")));
    }
}
