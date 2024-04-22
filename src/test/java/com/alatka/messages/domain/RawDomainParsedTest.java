package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RawDomainParsedTest {

    private RawDomainParsed domainParsed = new RawDomainParsed();

    @Test
    @DisplayName("getOrder() == 1000")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(1000, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setStatus(FieldDefinition.Status.NO_PARSE);

        boolean matched = domainParsed.matched(null, fieldDefinition);

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
    @DisplayName("unpack() ")
    void test04() {
        // TODO
    }

}