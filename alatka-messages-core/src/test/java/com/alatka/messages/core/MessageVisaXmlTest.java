package com.alatka.messages.core;

import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.IsoXmlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.message.BitmapSubdomainMessageBuilder;
import com.alatka.messages.core.message.MessageBuilder;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MessageVisaXmlTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoXmlMessageDefinitionBuilder().build();
    }

    private void doTest(String hex) {
        String key = "iso:visa:common:payload";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), hex.toUpperCase());

        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder1, holder);
    }

    @Test
    public void test01() {
        this.doTest("F0F1F0F0000000000000000209E00000000010002000");
    }

    @Test
    public void test02() {
        this.doTest("F0F1F0F0000000000000000217E08000100020003000F1F2F3F3F4404040404040404040");
    }

    @Test
    public void test03() {
        MessageHolder payload = MessageHolder.newInstance("iso:visa:common:header");
        payload.put(1, 20);
        payload.put(14, 6610);

        MessageDefinitionContext context = MessageDefinitionContext.getInstance();
        MessageDefinition definition = context.messageDefinition("iso:visa:common:header");
        byte[] pack = new BitmapSubdomainMessageBuilder(definition).pack(payload);
        System.out.println(BytesUtil.bytesToHex(pack));
        MessageHolder unpack = new BitmapSubdomainMessageBuilder(definition).unpack(pack);
        System.out.println(unpack);
    }


}
