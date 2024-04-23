package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinaryLVDomainParsedTest {

    private LVDomainParsed domainParsed = new BinaryLVDomainParsed();

    @Test
    @DisplayName("getOrder() == 30")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(30, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);

        boolean matched = domainParsed.matched(messageDefinition, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("长度域int->bytes BCD")
    void test03() {
        int length = 20;
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(2);
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        byte[] bytes = domainParsed.intToBytes(length, fieldDefinition);
        Assertions.assertArrayEquals(bytes, new byte[]{0, 40});
    }

    @Test
    @DisplayName("长度域int->bytes !BCD")
    void test04() {
        int length = 20;
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(2);
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        byte[] bytes = domainParsed.intToBytes(length, fieldDefinition);
        Assertions.assertArrayEquals(bytes, new byte[]{0, 20});
    }

    @Test
    @DisplayName("长度域bytes->int BCD length偶数")
    void test05() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes("14"), fieldDefinition);
        Assertions.assertEquals(length, 10);
    }

    @Test
    @DisplayName("长度域bytes->int BCD length奇数")
    void test06() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes("15"), fieldDefinition);
        Assertions.assertEquals(length, 11);
    }

    @Test
    @DisplayName("长度域bytes->int !BCD")
    void test07() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);
        int length = domainParsed.bytesToInt(BytesUtil.hexToBytes("14"), fieldDefinition);
        Assertions.assertEquals(length, 20);
    }

}