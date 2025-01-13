package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BcdicLVDomainParsedTest {

    private LVDomainParsed domainParsed = new EbcdicLVDomainParsed();

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
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
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
        fieldDefinition.setLength(3);
        byte[] bytes = domainParsed.intToBytes(length, fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "F0F2F0");
    }

    @Test
    @DisplayName("长度域bytes->int")
    void test04() {
        String hex = "F0F2F0";
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes(hex), null);
        Assertions.assertEquals(length, 20);
    }

}