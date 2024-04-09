package com.alatka.messages;

import com.alatka.messages.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MessageCupsTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder().build();
    }

    @Test
    public void test01() {
        String key = "iso:cups:common:payload";
        String hex = "30323030E23A44C1A8E098120000000010000081313636323531383830303030303531363838333030303030303832353131323430303836333734333131323430303038323530383235363031313032313032303630383932323530303030303839323235303030303334363235313838303030303035313638383D3238303535303130303030303030303931303030303030303231363830313233343536373831323334353637383930313233343543484E32393030304348494E4120554E494F4E5041592053494D554C41544F522020202020202020313536000000000000000032343030303030303030303030303030303237303030303035303030313030303030303030303034303231303032303231534D303136E069374A9BC6AB9A659912BE707F824330383033303530303031303433353131533030303030303032353033303530303031202020303030303030303030303030303041413030303044363132303134";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test02() {
        String key = "iso:cups:common:payload";
        String hex = "30323130E23E40818AC0801000000000100000813136363235313838303030303035313638383330303030303038323531313234303038363337343331313234303030383235303030303038323536303131303230383932323530303030303839323235303030303030303030303032313638303134313233343536373831323334353637383930313233343531353630333030303030303530303031303030303030303030303430323130303230303030383033303530303031303433353131533030303030303032353033303530303031202020303030303030303030303030303041413030304233333537423636";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test03() {
        String key = "iso:cups:common:payload";
        String hex = "30323030E23A46C1A8E09A1200000000100000813136363232363031303536323838353437393330303030303038323531323330313138363337383731323330313130383235303832353630313130353130303130323036303839323235303030303038393232353030303033323632323630313035363238383534373944333131323939393132333435313438303030303030303231363831313233343536373831323334353637383930313233343543484E32393030304348494E4120554E494F4E5041592053494D554C41544F5220202020202020203135360000000000000000323430303030303030303030303030303132309F2608C20428A3FF53F0AD9F2701809F101307020103AB806010000000000900000C060A299F37040B7FBD489F36023788950500001800009A032308259C01019F02060000000000005F2A02015682027D009F1A0201569F03060000000000009F3303E098C09F34009F3501229F1E083230303331323330303237303030303036303030313030303030303030303034303232313032303231534D303136B243A0CBC3A624D6F3DF920B10FAB4EA30383033303530303031303433353131533030303030303032353033303530303031202020303030303030303030303030303041413030303133423933354343";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        // F55_F0x9F34长度为0
        // Assertions.assertEquals(kex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test04() {
        String key = "iso:cups:common:payload";
        String hex = "30323130E23E42818AE0861200000000100000A13136363232363031303536323838353437393330303030303038323531323330313138363337383731323330313130383235303030303038323536303131303031303230383932323530303030303839323235303030303030303030303032313638313030313233343536373831323334353637383930313233343543484E32393030304348494E4120554E494F4E5041592053494D554C41544F522020202020202020313536303430333030313135364330303030303137393731303033303032313536433030303030343831303933383031379F36023788910AE9D1DC942D1F3D673030303330303030303036303030313030303030303030303034303232313032313030303231534D303136749942921C07D1AA46462560C4B80B4F30383030393830303031303433353131533030303030303032353033303530303031202020303030303030303030303030303041413030303232364E20203030303030343831303933383030303031303030303020203030303020202020303531202B3030303137393731303034383131303030303030303030303030202020202020202020202020202020202020202020202020202020313020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303830323030333030303233423031464345";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
    }

    @Test
    public void test05() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test06() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test07() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test08() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test09() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    public void test10() {
        String key = "iso:cups:common:payload";
        String hex = "";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }
}
