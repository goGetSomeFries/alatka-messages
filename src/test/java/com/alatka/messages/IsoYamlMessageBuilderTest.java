package com.alatka.messages;

import com.alatka.messages.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IsoYamlMessageBuilderTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder().build();
    }

    @Test
    @DisplayName("消息头")
    public void test01() {
        String key = "iso:cups:test:header";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, 46);
        instance.put(2, "10000001");
        instance.put(3, 1024);
        instance.put(4, "wroieqruei");
        instance.put(5, "dajfkdlsja");
        instance.put(6, "");
        instance.put(7, "00000000");
        instance.put(8, "qazwsxed");
        instance.put(9, "00000000");
        instance.put(10, "00000");

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("各类型域")
    public void test02() {
        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(2, "6225880155238798");
        instance.put(3, "000000");
        instance.put(4, new BigDecimal("10000012"));
        instance.put(7, LocalDateTime.now());
        instance.put(9, null); // TODO
        instance.put(11, new Random().nextInt(1000000));
        instance.put(12, LocalTime.now());
        instance.put(13, MonthDay.now());
        instance.put(14, YearMonth.now());
        instance.put(25, "83");
        instance.put(28, null); // TODO
        instance.put(32, "220881");
        instance.put(35, null); // TODO
        instance.put(36, null); // TODO
        instance.put(45, null); // TODO

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    @DisplayName("子域:UV单条")
    public void test03() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F48:AA");
        instance1.put(1, "DJIEJ47824781IFQIJFJOEJKL:SJE$@!!");
        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(48, usageSubdomain);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV多条")
    public void test04() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F48:PB");
        instance1.put(1, "DJI");
        instance1.put(2, "1");
        MessageHolder instance2 = MessageHolder.newInstance("iso:cups:test:subPayload:F48:RP");
        instance2.put(1, "DEIJ888FJEIJIL");
        instance2.put(2, null);
        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);
        usageSubdomain.put(instance2);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(48, usageSubdomain);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        System.out.println(object);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV-分页")
    public void test05() {
        List<MessageHolder> list = new ArrayList<>();
        MessageHolder instance11 = MessageHolder.newInstance("iso:cups:test:subPayload:F59@QR_F3");
        instance11.put(1, 1);
        instance11.put(2, LocalDate.now());
        instance11.put(3, "156");
        instance11.put(4, "15400");
        instance11.put(5, "45700000");
        instance11.put(6, "test");
        MessageHolder instance12 = MessageHolder.newInstance("iso:cups:test:subPayload:F59@QR_F3");
        instance12.put(1, 1);
        instance12.put(2, LocalDate.now());
        instance12.put(3, "156");
        instance12.put(4, "15400");
        instance12.put(5, "45700000");
        instance12.put(6, "test");
        list.add(instance11);
        list.add(instance12);

        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F59:QR");
        instance1.put(1, "156");
        instance1.put(2, 2);
        instance1.put(3, list);

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(59, usageSubdomain);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV-TV")
    public void test06() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F62:IO");
        instance1.put(1, "VIS");
        instance1.put(2, LocalDateTime.now());
        instance1.put(3, "838822");
        instance1.put(4, "8977");
        instance1.put(5, "87987657678");
        instance1.put(6, "87987657678");
        instance1.put(7, "00");
        instance1.put(8, "000000000033");
        instance1.put(9, "99999999");
        instance1.put(10, null);
        instance1.put(11, null);
        instance1.put(12, null);

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(62, usageSubdomain);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:TLV")
    public void test07() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F55");
        instance1.put(0x9A, LocalDate.now());
        instance1.put(0x9C, 1);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(55, instance1);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:DEFAULT")
    public void test08() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F53");
        instance1.put(1, "D");
        instance1.put(2, "1");
        instance1.put(3, null);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(53, instance1);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:DEFAULT-UV")
    public void test09() {
        MessageHolder instance11 = MessageHolder.newInstance("iso:cups:test:subPayload:F61_F10:CR");
        instance11.put(1, "K");

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance11);

        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F61");
        instance1.put(1, "01");
        instance1.put(2, "220881199873641934");
        instance1.put(3, "0");
        instance1.put(4, "1");
        instance1.put(5, "134");
        instance1.put(6, "3");
        instance1.put(7, "E");
        instance1.put(8, "D");
        instance1.put(9, "U");
        instance1.put(10, usageSubdomain);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(61, instance1);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:ULV-TLV")
    public void test10() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:test:subPayload:F56:PR");
        instance1.put(0x01, "DJK");

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:test:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(56, usageSubdomain);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void test98() {
        String hex = "30323030E23A44C1A8E098120000000010000081313636323531383830303030303531363838333030303030303832353131323430303836333734333131323430303038323530383235363031313032313032303630383932323530303030303839323235303030303334363235313838303030303035313638383D3238303535303130303030303030303931303030303030303231363830313233343536373831323334353637383930313233343543484E32393030304348494E4120554E494F4E5041592053494D554C41544F522020202020202020313536000000000000000032343030303030303030303030303030303237303030303035303030313030303030303030303034303231303032303231534D303136E069374A9BC6AB9A659912BE707F824330383033303530303031303433353131533030303030303032353033303530303031202020303030303030303030303030303041413030303044363132303134";
        MessageHolder holder = MessageBuilder.init("iso:cups:common:payload").unpack(hex);
//        byte[] pack = MessageBuilder.init("iso:cups:common:payload").pack(holder);
//        Assertions.assertEquals(hex, BytesUtil.bytesToHex(pack));
        System.out.println(holder);
        System.out.println(Arrays.toString("0".getBytes()));
    }

    @Test
    public void test99() {
        String hex = "f0f1f0f0767d448188e1a008103568560090000176000000000000000176000000000176122913412700000001000172134127122925041229639981000008885280000888518000f0f0f0f1f7f2f1f2f1f3f2f7f3f9f4f5f3f34040f3f2f5f6f2f2f0f340404040404040d1c3c240e3c5e2e340d4c5d9c3c8c1d5e3404040404040e3c5e2e340c3c9e3e84040404040d1d7d54af0f2f4f71122334455667788990011223344556677889900000201094725454745445395202545900000000006404040404040f0f3f1f4f9f6f5f8f7f4404040f1f4404040f0f4f0f1f0f8f4f0f8f4f006f2f2f2f3f9f2";
        MessageHolder holder = MessageBuilder.init("iso:jcb:common:payload").unpack(hex);
//        byte[] pack = MessageBuilder.init("iso:cups:common:payload").pack(holder);
//        Assertions.assertEquals(hex, BytesUtil.bytesToHex(pack));
        System.out.println(holder);
    }

}
