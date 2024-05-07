package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TLV2DomainParsedTest {

    private TLV2DomainParsed domainParsed = new TLV2DomainParsed();

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
        messageDefinition.setDomainType(MessageDefinition.DomainType.TLV2);

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
        fieldDefinition.setAliasName("T00");
        byte[] pack = domainParsed.pack(value.getBytes(), fieldDefinition);
        Assertions.assertEquals(new String(pack), "T00004WLZF");
    }

    @Test
    @DisplayName("unpack()")
    void test06() {
        String str = "T00004WLZF";
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(str.getBytes(), null, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "574C5A46");
    }

}