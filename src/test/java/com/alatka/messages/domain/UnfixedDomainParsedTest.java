package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class UnfixedDomainParsedTest {

    private UnfixedDomainParsed domainParsed = new UnfixedDomainParsed();

    @Test
    @DisplayName("getOrder() == 40")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(40, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);
        fieldDefinition.setLength(-1);

        boolean matched = domainParsed.matched(messageDefinition, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        byte[] bytes = BytesUtil.hexToBytes("11");
        byte[] pack = domainParsed.pack(bytes, null);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("unpack()")
    void test06() {
        byte[] bytes = BytesUtil.hexToBytes("010203040506070809");
        AtomicInteger counter = new AtomicInteger(6);
        byte[] unpack = domainParsed.unpack(bytes, null, counter);
        Assertions.assertEquals("070809", BytesUtil.bytesToHex(unpack));
    }

    @Test
    @DisplayName("unpack() exception")
    void test07() {
        byte[] bytes = BytesUtil.hexToBytes("010203040506070809");
        AtomicInteger counter = new AtomicInteger(9);
        Assertions.assertThrows(IllegalArgumentException.class, () -> domainParsed.unpack(bytes, null, counter));
    }
}