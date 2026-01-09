package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TLV3DomainParsedTest {

    private TLV3DomainParsed domainParsed = new TLV3DomainParsed();

    @Test
    @DisplayName("getOrder() == 70")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(70, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setDomainType(MessageDefinition.DomainType.TLV3);

        boolean matched = domainParsed.matched(messageDefinition, null);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack() byte.length=0")
    void test03() {
        byte[] bytes = new byte[0];
        byte[] pack = domainParsed.pack(bytes, null);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("pack()")
    void test04() {
        String value = "WLZF";
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setAliasName("002");
        byte[] pack = domainParsed.pack(BytesUtil.toEBCDIC(value), fieldDefinition);
        Assertions.assertEquals(BytesUtil.fromEBCDIC(pack), "002004WLZF");
    }

    @Test
    @DisplayName("unpack()")
    void test06() {
        String str = "002004WLZF";
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(BytesUtil.toEBCDIC(str), null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "E6D3E9C6");
    }

    @Test
    @DisplayName("tagLength()")
    void test07() {
        Assertions.assertEquals(3, domainParsed.tagLength());
    }

    @Test
    @DisplayName("lenLength()")
    void test08() {
        Assertions.assertEquals(3, domainParsed.lenLength());
    }

    @Test
    @DisplayName("buildTagBytes()")
    void test9() {
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("F0F0F3"), domainParsed.buildTagBytes("003"));
    }

    @Test
    @DisplayName("buildLenBytes()")
    void test10() {
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("F0F0F3"), domainParsed.buildLenBytes("003"));
    }

    @Test
    @DisplayName("buildLen()")
    void test11() {
        String hex = "F5F0";
        Assertions.assertEquals(50, domainParsed.buildLen(BytesUtil.hexToBytes(hex)));
    }
}