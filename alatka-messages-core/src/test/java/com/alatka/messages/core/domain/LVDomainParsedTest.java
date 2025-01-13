package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class LVDomainParsedTest {

    private LVDomainParsed domainParsed = new BinaryLVDomainParsed();

    @Test
    @DisplayName("raw()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setClassType(String.class);

        boolean matched = domainParsed.raw(fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack() raw()==true")
    void test03() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setClassType(String.class);
        byte[] bytes = BytesUtil.hexToBytes("102E3849");

        byte[] pack = domainParsed.pack(bytes, fieldDefinition);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("pack() raw()==false")
    void test04() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        fieldDefinition.setLength(1);
        byte[] bytes = BytesUtil.hexToBytes("102E3849");

        byte[] pack = domainParsed.pack(bytes, fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "04102E3849");
    }

    @Test
    @DisplayName("pack() bcd")
    void test05() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setClassType(Integer.class);
        fieldDefinition.setLength(1);
        byte[] bytes = BytesUtil.hexToBytes("01030904");

        byte[] pack = domainParsed.pack(bytes, fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "0801030904");
    }

    @Test
    @DisplayName("unpack() 数据域超长")
    void test06() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(4);
        byte[] bytes = BytesUtil.hexToBytes("051E2E3E4E5E");
        AtomicInteger counter = new AtomicInteger(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> domainParsed.unpack(bytes, fieldDefinition, counter));
    }

    @Test
    @DisplayName("unpack() raw()==true")
    void test07() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setClassType(String.class);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(5);
        byte[] bytes = BytesUtil.hexToBytes("0A1234567890");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), "0A1234567890");
    }

    @Test
    @DisplayName("unpack() raw()==false")
    void test08() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(5);
        byte[] bytes = BytesUtil.hexToBytes("051E2E3E4E5E");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(unpack), "1E2E3E4E5E");
    }

    @Test
    @DisplayName("unpack() BCD length偶数")
    void test09() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setLenParseType(FieldDefinition.ParseType.BINARY);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(10);
        byte[] bytes = BytesUtil.hexToBytes("0A1234567890");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals("1234567890", BytesUtil.bytesToHex(unpack));
    }

    @Test
    @DisplayName("unpack() BCD length奇数")
    void test10() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        fieldDefinition.setLenParseType(FieldDefinition.ParseType.BINARY);
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(1);
        fieldDefinition.setMaxLength(10);
        byte[] bytes = BytesUtil.hexToBytes("090123456789");
        AtomicInteger counter = new AtomicInteger(0);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals("0123456789", BytesUtil.bytesToHex(unpack));
    }
}