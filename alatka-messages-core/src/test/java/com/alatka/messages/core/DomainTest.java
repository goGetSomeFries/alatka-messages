package com.alatka.messages.core;

import com.alatka.messages.core.definition.FixedYamlMessageDefinitionBuilder;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.message.MessageBuilder;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("各类型域")
public class DomainTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder("messages").build();
        new FixedYamlMessageDefinitionBuilder("messages").build();
    }

    private void doTest(Object instance, String key) {
        MessageBuilder messageBuilder = MessageBuilder.init(key);

        byte[] expected = messageBuilder.pack(instance);
        MessageHolder expectedObj = messageBuilder.unpack(expected);
        System.out.println(expectedObj);
        byte[] actual = messageBuilder.pack(expectedObj);
        MessageHolder actualObj = messageBuilder.unpack(actual);

        Assertions.assertEquals(BytesUtil.bytesToHex(expected), BytesUtil.bytesToHex(actual));
        Assertions.assertEquals(expectedObj, actualObj);
    }

    @Disabled
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
    @DisplayName("子域:UVAS单条")
    public void test02() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F48:AA");
        instance1.put(1, "DJIEJ47824781IFQIJFJOEJKLSJE");
        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(48, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:UVAS多条")
    public void test03() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F48:PB");
        instance1.put(1, "DJI");
        instance1.put(2, "1");
        MessageHolder instance2 = MessageHolder.newInstance("iso:cups:common:subPayload:F48:RP");
        instance2.put(1, "PRODUCT_CODE123");
        instance2.put(2, null);
        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);
        usageSubdomain.put(instance2);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(48, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:FIXED;fixed=true")
    public void test04() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F53");
        instance1.put(1, "D");
        instance1.put(2, "1");
        instance1.put(3, null);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(53, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:FIXED;fixed=false")
    public void test05() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F54");
        instance1.put(1, "AD");
        instance1.put(2, "11");
        instance1.put(3, "156");
        instance1.put(4, "-");
        instance1.put(5, new BigDecimal("12229"));

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(54, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:FIXED;fixed=false;last field length=-1")
    public void test06() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F122");
        instance1.put(1, "2342");
        instance1.put(2, "11DJKEJIFJKJKFJDKJFDJFKDAAA");

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(122, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:UV")
    public void test07() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F59:QL");
        instance1.put(1, 1);
        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(59, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:TLV")
    public void test08() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F55");
        instance1.put(7, LocalDate.now());
        instance1.put(8, 1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(55, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:ULV")
    public void test09() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F63:SM");
        instance1.put(1, BytesUtil.hexToBytes("12EB34592309129023908978"));

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(63, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:ULV2")
    public void test10() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:jcb:common:subPayload:F60:01");
        instance1.put(1, "1");
        MessageHolder instance2 = MessageHolder.newInstance("iso:jcb:common:subPayload:F60:02");
        instance2.put(1, "2");
        MessageHolder instance3 = MessageHolder.newInstance("iso:jcb:common:subPayload:F60:03");
        instance3.put(1, "3");

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);
        usageSubdomain.put(instance2);
        usageSubdomain.put(instance3);

        String key = "iso:jcb:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(60, usageSubdomain);
        instance.put(70, "1234");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:LIST")
    public void test11() {
        MessageHolder instance1 = MessageHolder.newInstance("fixed:9001:3006:subPayload:F13");
        instance1.put(1, 1);
        instance1.put(2, LocalDate.now());
        instance1.put(3, LocalDate.now());
        instance1.put(5, 23423);
        MessageHolder instance2 = MessageHolder.newInstance("fixed:9001:3006:subPayload:F13");
        instance2.put(1, 2);
        instance2.put(2, LocalDate.now());
        instance2.put(3, LocalDate.now());
        instance2.put(5, 23425);

        String key = "fixed:9001:3006:response";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, "3006");
        instance.put(2, "000000");
        instance.put(3, "9001");
        instance.put(4, "PR");
        instance.put(5, "900999");
        instance.put(6, "000000");
        instance.put(7, 573689);
        instance.put(8, "622544495959234");
        instance.put(9, YearMonth.now());
        instance.put(10, "1");
        instance.put(11, 2);
        instance.put(12, "1");
        instance.put(13, Stream.of(instance1, instance2).collect(Collectors.toList()));

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:UV$TV")
    public void test12() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F62:IO");
        instance1.put(1, "VIS");
        instance1.put(2, LocalDateTime.now());
        instance1.put(3, "838822");
        instance1.put(4, "8977");
        instance1.put(5, "87987657678");
        instance1.put(6, "87987657678");
        instance1.put(7, null);
        instance1.put(8, "000000000033");
        instance1.put(9, "99999999");
        instance1.put(10, null);
        instance1.put(11, null);
        instance1.put(12, null);

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(62, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:UV$TLV2")
    public void test13() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cupd:common:subPayload:F62:DA");
        instance1.put(1, "VIS");
        instance1.put(2, "0020");
        instance1.put(3, "EJIFJEIJFALEUBNN");
        instance1.put(4, null);
        instance1.put(5, null);
        instance1.put(6, null);

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cupd:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(62, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:ULV$TLV")
    public void test14() {
        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F56:PR");
        instance1.put(1, "DJK");

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(56, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:UV-LIST")
    public void test15() {
        List<MessageHolder> list = new ArrayList<>();
        MessageHolder instance11 = MessageHolder.newInstance("iso:cups:common:subPayload:F59$QR_F3");
        instance11.put(1, 1);
        instance11.put(2, LocalDate.now());
        instance11.put(3, "156");
        instance11.put(4, "15400");
        instance11.put(5, "45700000");
        instance11.put(6, "test");
        MessageHolder instance12 = MessageHolder.newInstance("iso:cups:common:subPayload:F59$QR_F3");
        instance12.put(1, 1);
        instance12.put(2, LocalDate.now());
        instance12.put(3, "156");
        instance12.put(4, "15400");
        instance12.put(5, "45700000");
        instance12.put(6, "test");
        list.add(instance11);
        list.add(instance12);

        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F59:QR");
        instance1.put(1, "156");
        instance1.put(2, 2);
        instance1.put(3, list);

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(59, usageSubdomain);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:FIXED-FIXED")
    public void test16() {
        MessageHolder instance11 = MessageHolder.newInstance("iso:cups:common:subPayload:F60_F11");
        instance11.put(1, "K1");
        instance11.put(2, "K");
        instance11.put(3, "KMK");
        instance11.put(4, "K");
        instance11.put(5, "K");
        instance11.put(6, "K");
        instance11.put(7, "K");
        instance11.put(8, "K9");
        instance11.put(9, "K");
        instance11.put(10, "22");


        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F60");
        instance1.put(1, "0000");
        instance1.put(2, "A");
        instance1.put(3, "0");
        instance1.put(4, "1");
        instance1.put(5, "1");
        instance1.put(6, "31");
        instance1.put(7, "E");
        instance1.put(8, "D");
        instance1.put(9, "UA");
        instance1.put(10, "Q");
        instance1.put(11, instance11);

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(60, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

    @Test
    @DisplayName("子域:FIXED-UV")
    public void test17() {
        MessageHolder instance11 = MessageHolder.newInstance("iso:cups:common:subPayload:F61_F10:CR");
        instance11.put(1, "K");

        UsageSubdomain<MessageHolder> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance11);

        MessageHolder instance1 = MessageHolder.newInstance("iso:cups:common:subPayload:F61");
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

        String key = "iso:cups:common:payload";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(0, "0100");
        instance.put(61, instance1);
        instance.put(128, "00000000");

        this.doTest(instance, key);
    }

}
