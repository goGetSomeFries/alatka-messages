package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedDomainParsedTest {

    private FixedDomainParsed domainParsed = new FixedDomainParsed();

    @Test
    @DisplayName("getOrder() == 0")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(0, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);

        boolean matched = domainParsed.matched(null, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        String str = "12345";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(10);
        fieldDefinition.setOriginClass(String.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);
        byte[] pack = domainParsed.pack(str.getBytes(), fieldDefinition);
        Assertions.assertEquals(new String(pack), str + "     ");
    }

    @Test
    @DisplayName("unpack()")
    void test04() {
        String str = "T01123456789";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(9);
        AtomicInteger counter = new AtomicInteger(3);

        byte[] pack = domainParsed.unpack(str.getBytes(), fieldDefinition, counter);
        Assertions.assertEquals(BytesUtil.bytesToHex(pack), "313233343536373839");
    }

}