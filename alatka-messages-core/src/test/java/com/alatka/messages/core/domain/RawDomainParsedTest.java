package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RawDomainParsedTest {

    private RawDomainParsed domainParsed = new RawDomainParsed();

    @Test
    @DisplayName("getOrder() == 1000")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(1000, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setStatus(FieldDefinition.Status.RAW);

        boolean matched = domainParsed.matched(null, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        byte[] bytes = "34243".getBytes();
        byte[] pack = domainParsed.pack(bytes, null);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("unpack() 定长域")
    void test04() {
        String str = "T01123456789";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);
        fieldDefinition.setLength(9);
        AtomicInteger counter = new AtomicInteger(3);

        byte[] pack = domainParsed.unpack(str.getBytes(), fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "313233343536373839");
    }

    @Test
    @DisplayName("unpack() 定长域")
    void test05() {
        String str = "T01123456789";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);
        fieldDefinition.setLength(9);
        AtomicInteger counter = new AtomicInteger(3);

        byte[] pack = domainParsed.unpack(str.getBytes(), fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "313233343536373839");
    }

    @Test
    @DisplayName("unpack() 不定长域 L=BINARY")
    void test06() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setLenParseType(FieldDefinition.ParseType.BINARY);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(5);
        byte[] bytes = BytesUtil.hexToBytes("051E2E3E4E5E");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), "051E2E3E4E5E");
    }

    @Test
    @DisplayName("unpack() 不定长域 L=ASCII")
    void test07() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setLenParseType(FieldDefinition.ParseType.ASCII);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(2);
        fieldDefinition.setMaxLength(5);
        byte[] bytes = BytesUtil.hexToBytes("30351E2E3E4E5E");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), "30351E2E3E4E5E");
    }

    @Test
    @DisplayName("unpack() 不定长域 L=EBCDIC")
    void test08() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setLenParseType(FieldDefinition.ParseType.EBCDIC);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(2);
        fieldDefinition.setMaxLength(5);
        byte[] bytes = BytesUtil.hexToBytes("F0F51E2E3E4E5E");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), "F0F51E2E3E4E5E");
    }
}