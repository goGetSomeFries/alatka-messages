package com.alatka.messages;

import com.alatka.messages.definition.FixedXmlMessageDefinitionBuilder;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Message9001XmlTest {

    @BeforeAll
    public static void beforeAll() {
        new FixedXmlMessageDefinitionBuilder().build();
    }

    private void doTest(String hex, String key) {
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), hex.toUpperCase());

        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder1, holder);
    }

    @Test
    @DisplayName("4118 request")
    public void test01() {
        String hex = "343131382020202020203033303539393939393050523030303030303234343830334331202020202020323231383831313939343338323733323838202020202020202020202020202020202020202043555441472020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020";
        String key = "fixed:9001:4118:request";
        this.doTest(hex, key);
    }

    @Test
    @DisplayName("4118 response")
    public void test02() {
        String hex = "3431313830303030303030333035393939393930505230303030303038393139303943312020202020203232313838313139393433383237333238382020202020202020202020202020202020202020435554414720202020B2E2CAD4746167B1EAC7A92020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020";
        String key = "fixed:9001:4118:response";
        this.doTest(hex, key);
    }

    @Test
    @DisplayName("3006 request")
    public void test03() {
        String hex = "33303036202020202020303330355052393939393930303030303030343136393735363232353434343935393539323334202020203234303531313230323430353031323032343035303131383336353230383030303030302020202020202020202020";
        String key = "fixed:9001:3006:request";
        this.doTest(hex, key);
    }

    @Test
    @DisplayName("3006 response")
    public void test04() {
        String hex = "33303036303030303030303330353939393939305052303030303030353733363839363232353434343935393539323334202020203234303531303231303031323032343035303132303234303530313138323535313631303339333932444441433030303030303230302E30312020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020303032323032343035303132303234303530313138323535313632303339333938444441433030303030303236322E30332020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020";
        String key = "fixed:9001:3006:response";
        this.doTest(hex, key);
    }

    @Test
    @DisplayName("sms payload")
    public void test05() {
        String hex = "33303036303030303030";
        String key = "fixed:9001:sms:payload";
        this.doTest(hex, key);
    }

}
