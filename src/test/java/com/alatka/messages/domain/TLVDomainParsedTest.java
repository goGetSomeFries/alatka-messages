package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TLVDomainParsedTest {

    private TLVDomainParsed domainParsed = new TLVDomainParsed();

    @Test
    void getOrder() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(80, order);
    }

    @Test
    void matched() {
    }

    @Test
    void pack() {
        byte[] bytes = null;
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setDomainNo(1);
        byte[] actual = domainParsed.pack(bytes, fieldDefinition);
        Assertions.assertArrayEquals(null, actual);
    }

    @Test
    void unpack() {
    }
}