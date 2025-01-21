package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class BitmapDomainParsedTest {

    private final BitmapDomainParsed domainParsed = new BitmapDomainParsed();

    @Test
    @DisplayName("getOrder() == 100")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(100, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setKind(MessageDefinition.Kind.payload);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setDomainNo(1);

        boolean matched = domainParsed.matched(messageDefinition, fieldDefinition);

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
    @DisplayName("unpack() 64bit")
    void test04() {
        String hex = "6A435E3567A12345";
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(BytesUtil.hexToBytes(hex), null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), hex);
    }

    @Test
    @DisplayName("unpack() 128bit")
    void test05() {
        String hex = "8A435E3567A123456A435E3567A12345";
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(BytesUtil.hexToBytes(hex), null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), hex);
    }

    @Test
    @DisplayName("unpack() 192bit")
    void test06() {
        String hex = "8A435E3567A123458A435E3567A123456A435E3567A12345";
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(BytesUtil.hexToBytes(hex), null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), hex);
    }
}