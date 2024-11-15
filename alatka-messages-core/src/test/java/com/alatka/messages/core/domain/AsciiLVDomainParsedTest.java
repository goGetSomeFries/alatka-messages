package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AsciiLVDomainParsedTest {

    private LVDomainParsed domainParsed = new AsciiLVDomainParsed();

    @Test
    @DisplayName("getOrder() == 20")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(20, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);

        boolean matched = domainParsed.matched(messageDefinition, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("长度域int->bytes")
    void test03() {
        int length = 20;
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(3);
        byte[] bytes = domainParsed.intToBytes(length, fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "303230");
    }

    @Test
    @DisplayName("长度域bytes->int")
    void test04() {
        String hex = "303230";
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes(hex), null);
        Assertions.assertEquals(length, 20);
    }

}