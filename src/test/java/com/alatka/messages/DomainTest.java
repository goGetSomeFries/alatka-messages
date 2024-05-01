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
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DisplayName("各类型域")
public class DomainTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder().build();
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
    @DisplayName("各类型域")
    public void test01() {
        String key = "iso:cups:common:payload";
        MessageHolder expectedObj = MessageHolder.newInstance(key);
        expectedObj.put(0, "0100"); // String类型 定长
        expectedObj.put(2, "6225880155238798"); // String类型 变长
        expectedObj.put(3, null); // String类型 定长右补空格
        expectedObj.put(4, new BigDecimal("10000012")); // BigDecimal类型 定长左补0
        expectedObj.put(7, LocalDateTime.now()); // LocalDateTime类型 定长
        expectedObj.put(9, 100); // Integer类型 定长左补0
        expectedObj.put(11, new Random().nextInt(1000000)); // Integer类型 定长
        expectedObj.put(12, LocalTime.now()); // LocalTime类型 定长
        expectedObj.put(13, MonthDay.now()); // MonthDay类型 定长
        expectedObj.put(14, YearMonth.now()); // YearMonth类型 定长
        expectedObj.put(25, "83"); // String类型 定长
        expectedObj.put(28, null); // TODO
        expectedObj.put(32, "220881");
        expectedObj.put(35, null); // TODO
        expectedObj.put(36, null); // TODO
        expectedObj.put(45, null); // TODO

        byte[] expectedBytes = MessageBuilder.init(key).pack(expectedObj);
        MessageHolder actualObj = MessageBuilder.init(key).unpack(expectedBytes);
        byte[] actualBytes = MessageBuilder.init(key).pack(actualObj);

        Assertions.assertEquals(BytesUtil.bytesToHex(expectedBytes), BytesUtil.bytesToHex(actualBytes));
//        Assertions.assertEquals(expectedObj, actualObj);
    }

    @Test
    @DisplayName("子域:UV")
    public void test02() {
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
    @DisplayName("子域:UVAS")
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

}
