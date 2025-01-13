package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinaryLVDomainParsedTest {

    private LVDomainParsed domainParsed = new BinaryLVDomainParsed();

    @Test
    @DisplayName("getOrder() == 20")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(20, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setFixed(false);
        fieldDefinition.setParseType(FieldDefinition.ParseType.BINARY);
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);

        boolean matched = domainParsed.matched(messageDefinition, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("长度域int->bytes")
    void test03() {
        int length = 20;
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setLength(2);
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        byte[] bytes = domainParsed.intToBytes(length, fieldDefinition);
        Assertions.assertArrayEquals(bytes, new byte[]{0, 20});
    }

    @Test
    @DisplayName("长度域bytes->int")
    void test07() {
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes("14"), fieldDefinition);
        Assertions.assertEquals(length, 20);
    }

}