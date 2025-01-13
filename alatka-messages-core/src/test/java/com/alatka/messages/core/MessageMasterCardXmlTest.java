package com.alatka.messages.core;

import com.alatka.messages.core.definition.IsoXmlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.message.MessageBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MessageMasterCardXmlTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoXmlMessageDefinitionBuilder().build();
    }

    @Test
    public void test01() {
        String hex = "F0F1F0F0723C440180618008F1F6F5F5F0F6F9F0F0F1F4F0F1F0F0F3F0F5F0F0F0F0F0F0F0F0F0F0F0F0F0F1F2F3F4F5F0F9F0F4F1F1F3F7F2F3F0F4F3F5F1F7F1F1F3F7F2F3F0F9F0F4F2F5F0F3F5F3F1F1F8F1F0F0F6F0F1F3F0F5F8F0F6F0F1F3F0F5F8F1F2F3F4F5F6F7F8F9F1F1F1F1F1F1D1968895C39694978195A8404040404040404040404040D396958496954040404040404040E4E2C1F0F1F9D7F4F2F0F7F0F1F0F3F9F1F0F7F7F0F3D7F0F1F8F4F0F0F2F6F1F0F2F5F1F0F0F0F0F6F6F0F7F8F4F04040404040F0F3F4F0F0";
        String key = "iso:mastercard:common:payload";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);

        MessageHolder field48 = holder.getByDomainNo(48);
        String field48_1 = field48.getByDomainNo(1);
        System.out.println(field48_1);

        UsageSubdomain<MessageHolder> field48_2 = field48.getByDomainNo(2);
        MessageHolder field48_2_u42 = field48_2.get("42");
        String field48_2_u42_1 = field48_2_u42.getByDomainNo(1);
        System.out.println(field48_2_u42_1);
    }


}
