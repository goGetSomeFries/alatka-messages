package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TLVDomainParsedTest {

    private TLVDomainParsed domainParsed = new TLVDomainParsed();

    @Test
    @DisplayName("getOrder() == 80")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(80, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setDomainType(MessageDefinition.DomainType.TLV);

        boolean matched = domainParsed.matched(messageDefinition, null);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack() 数据域值为空")
    void test03() {
        byte[] bytes = new byte[0];
        byte[] pack = domainParsed.pack(bytes, null);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("pack() L>1B")
    void test04() {
        String hex = "010203040506070809";
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setAliasName("9F11");
        fieldDefinition.setLength(129);
        fieldDefinition.setFixed(true);
        fieldDefinition.setOriginClass(String.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);

        byte[] pack = domainParsed.pack(BytesUtil.hexToBytes(hex), fieldDefinition);
        String actual = "9F118181" + hex + IntStream.range(0, 120).mapToObj(i -> "20").collect(Collectors.joining());
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), actual);
    }

    @Test
    @DisplayName("pack() L=1B")
    void test05() {
        String hex = "010203040506070809";
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setAliasName("9F11");
        fieldDefinition.setFixed(false);

        byte[] pack = domainParsed.pack(BytesUtil.hexToBytes(hex), fieldDefinition);
        String actual = "9F1109" + hex;
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), actual);
    }

    @Test
    @DisplayName("unpack() L（length）为0")
    void test06() {
        byte[] bytes = BytesUtil.hexToBytes("9500");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals(0, unpack.length);
    }

    @Test
    @DisplayName("unpack() T=1B,L=1B")
    void test07() {
        String tagHex = "98";
        String lenHex = "07";
        String valueHex = "10111213141516";
        byte[] bytes = BytesUtil.hexToBytes(tagHex + lenHex + valueHex);
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), valueHex);
    }

    @Test
    @DisplayName("unpack() T=1B,L=2B")
    void test08() {
        String tagHex = "98";
        String lenHex = "820009";
        String valueHex = "101112131415161718";
        byte[] bytes = BytesUtil.hexToBytes(tagHex + lenHex + valueHex);
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), valueHex);
    }

    @Test
    @DisplayName("unpack() T=2B,L=1B")
    void test09() {
        String tagHex = "1F88";
        String lenHex = "06";
        String valueHex = "101112131415";
        byte[] bytes = BytesUtil.hexToBytes(tagHex + lenHex + valueHex);
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), valueHex);
    }

    @Test
    @DisplayName("unpack() T=2B,L=2B")
    void test10() {
        String tagHex = "9F88";
        String lenHex = "82000A";
        String valueHex = "10111213141516171819";
        byte[] bytes = BytesUtil.hexToBytes(tagHex + lenHex + valueHex);
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), valueHex);
    }
}