package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BitmapDomainParsedTest {

    private BitmapDomainParsed domainParsed = new BitmapDomainParsed();

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
        fieldDefinition.setOriginClass(Map.class);

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
}