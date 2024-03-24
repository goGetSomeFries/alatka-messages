package com.alatka.messages;

import com.alatka.messages.definition.IsoAnnotationMessageDefinitionBuilder;
import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.template.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IsoAnnotationMessageBuilderTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoAnnotationMessageDefinitionBuilder("com.alatka.messages.template").build();
    }

    @Test
    @DisplayName("消息头")
    public void test01() {
        String key = "iso:cups:test:header";
        IsoCupsHeader instance = new IsoCupsHeader();
        instance.setHeaderLength(46);
        instance.setVersion("10000001");
        instance.setMessageLength(1024);
        instance.setDestinationId("wroieqruei");
        instance.setSourceId("dajfkdlsja");
        instance.setReserved(new byte[]{0, 0, 0});
        instance.setBatchNum("00000000");
        instance.setTransInfo("qazwsxed");
        instance.setUserInfo("00000000");
        instance.setRejectCode("00000");

        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsHeader object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertEquals(object, instance);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("各类型域")
    public void test02() {
        String key = "iso:cups:test:payload";
        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setPrimaryAcctNum("6225880155238798");
        instance.setProcessingCode("000000");
        instance.setAmtTrans(new BigDecimal("10000012"));
        instance.setTransmsnDateTime(LocalDateTime.now());
        instance.setConvRateSettlmt(null); // TODO
        instance.setSysTraceAuditNum(new Random().nextInt(1000000));
        instance.setTimeLocalTrans(LocalTime.now());
        instance.setDateLocalTrans(MonthDay.now());
/*
        instance.put(14, YearMonth.now());
        instance.put(25, "83");
        instance.put(28, null); // TODO
        instance.put(32, "220881");
        instance.put(35, null); // TODO
        instance.put(36, null); // TODO
        instance.put(45, null); // TODO
*/

        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    @DisplayName("子域:UV单条")
    public void test03() {
        F48AA instance1 = new F48AA();
        instance1.setAcqInstAddtnlInfo("DJIEJ47824781IFQIJFJOEJKL:SJE$@!!");
        UsageSubdomain<F48> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setAddtnlDataPrivate(usageSubdomain);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV多条")
    public void test04() {
        F48PB instance1 = new F48PB();
        instance1.setServiceId("DJI");
        instance1.setIcCode("1");
        F48RP instance2 = new F48RP();
        instance2.setProductCode("DEIJ888FJEIJIL");
        instance2.setReversed(null);
        UsageSubdomain<F48> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);
        usageSubdomain.put(instance2);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setAddtnlDataPrivate(usageSubdomain);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV-分页")
    public void test05() {
        List<F59QRPage> list = new ArrayList<>();
        F59QRPage instance11 = new F59QRPage();
        instance11.setNumber(1);
        instance11.setTransDate(LocalDate.now());
        instance11.setCurrencyCode("156");
        instance11.setTransAmt("15400");
        instance11.setBalanceAmt("45700000");
        instance11.setMemoCode("test");
        F59QRPage instance12 = new F59QRPage();
        instance12.setNumber(2);
        instance12.setTransDate(LocalDate.now());
        instance12.setCurrencyCode("156");
        instance12.setTransAmt("15400");
        instance12.setBalanceAmt("45700000");
        instance12.setMemoCode("test");
        list.add(instance11);
        list.add(instance12);

        F59QR instance1 = new F59QR();
        instance1.setCurrencyCode("156");
        instance1.setCounts(2);
        instance1.setElements(list);

        UsageSubdomain<F59> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setDetailInqrng(usageSubdomain);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:UV-TV")
    public void test06() {
        F62IO instance1 = new F62IO();
        instance1.setF62f1("VIS");
        instance1.setF62f2(LocalDateTime.now());
        instance1.setF62f3("838822");
        instance1.setF62f4("8977");
        instance1.setF62f5("87987657678");
        instance1.setF62f6("87987657678");
        instance1.setF62f7("00");
        instance1.setF62f8("000000000033");
        instance1.setF62f9("99999999");
        instance1.setF62f10(null);
        instance1.setF62f11(null);
        instance1.setF62f12(null);

        UsageSubdomain<F62> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setSwitchingData(usageSubdomain);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:TLV")
    public void test07() {
        F55 instance1 = new F55();
        instance1.setTransDate(LocalDate.now());
        instance1.setTransType(1);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setIccData(instance1);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:DEFAULT")
    public void test08() {
        F53 instance1 = new F53();
        instance1.setKeyType("D");
        instance1.setEncryptionMethodUsed("1");
        instance1.setReserved(null);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setSecRelatdCtrlInfo(instance1);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:DEFAULT-UV")
    public void test09() {
        F61F10CR instance11 = new F61F10CR();
        instance11.setF61f10f1("K");

        UsageSubdomain<F61F10> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance11);

        F61 instance1 = new F61();
        instance1.setF61f1("01");
        instance1.setF61f2("220881199873641934");
        instance1.setCvvResult("0");
        instance1.setPvvResult("1");
        instance1.setF61f5("134");
        instance1.setF61f6(3);
        instance1.setF61f7("E");
        instance1.setF61f8("D");
        instance1.setF61f9("U");
        instance1.setF61f10(usageSubdomain);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setChAuthInfo(instance1);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("子域:ULV-TLV")
    public void test10() {
        F56PR instance1 = new F56PR();
        instance1.setTag01("DJK");

        UsageSubdomain<F56> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance1);

        IsoCupsPayload instance = new IsoCupsPayload();
        instance.setMessageType("0100");
        instance.setAddtnlData56(usageSubdomain);

        String key = "iso:cups:test:payload";
        byte[] expected = MessageBuilder.init(key).pack(instance);
        IsoCupsPayload object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void test99() {
        System.out.println(DateTimeFormatter.ofPattern("MMddHHmmss").format(LocalDateTime.now()));
    }

}
