package com.alatka.messages.message;

import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TLVSubdomainMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new TLVSubdomainMessageBuilder(definition));
    }

    @Test
    @DisplayName("doBuildFieldDefinitions()")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        TLVSubdomainMessageBuilder messageBuilder = new TLVSubdomainMessageBuilder(definition);

        byte[] bytes = BytesUtil.hexToBytes("9F2701809F3303E098C09F34036003029F3501229F36020013");
        List<String> list = messageBuilder.doBuildFieldDefinitions(bytes, null);
        String[] array = {"9F27", "9F33", "9F34", "9F35", "9F36"};
        Assertions.assertEquals(array.length, list.size());
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], list.get(i));
        }
    }
}
