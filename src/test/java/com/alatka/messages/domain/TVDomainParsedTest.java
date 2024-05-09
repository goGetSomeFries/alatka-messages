package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TVDomainParsedTest {

    private TVDomainParsed domainParsed = new TVDomainParsed();

    @Test
    @DisplayName("getOrder() == 90")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(90, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setDomainType(MessageDefinition.DomainType.TV);

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
        String value = "123456789";
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setAliasName("T01");
        fieldDefinition.setLength(9);
        fieldDefinition.setOriginClass(String.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);
        byte[] pack = domainParsed.pack(value.getBytes(), fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), BytesUtil.bytesToHex(("T01" + value).getBytes()));
    }

    @Test
    @DisplayName("unpack()")
    void test05() {
        String str = "T01123456789";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(9);
        AtomicInteger counter = new AtomicInteger(0);

        byte[] pack = domainParsed.unpack(str.getBytes(), fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "313233343536373839");
    }

}