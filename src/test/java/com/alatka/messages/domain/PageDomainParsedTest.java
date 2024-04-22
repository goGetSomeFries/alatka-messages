package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PageDomainParsedTest {

    private PageDomainParsed domainParsed = new PageDomainParsed();

    @Test
    @DisplayName("getOrder() == 10")
    void test01() {
        int order = domainParsed.getOrder();
        Assertions.assertEquals(10, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);
        fieldDefinition.setOriginClass(ArrayList.class);

        boolean matched = domainParsed.matched(null, fieldDefinition);

        Assertions.assertTrue(matched);
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        byte[] bytes = BytesUtil.hexToBytes("11");
        byte[] pack = domainParsed.pack(bytes, null);
        Assertions.assertSame(pack, bytes);
    }

    @Test
    @DisplayName("unpack()")
    void test06() {
        byte[] bytes = BytesUtil.hexToBytes("0102030405060708");
        AtomicInteger counter = new AtomicInteger(0);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setPageSizeName("count");

        Inner inner = new Inner();
        inner.setCount(2);
        domainParsed.setMessageHolder(inner);
        byte[] unpack = domainParsed.unpack(bytes, fieldDefinition, counter);
        Assertions.assertEquals("0102030405060708", BytesUtil.bytesToHex(unpack));
    }

    private class Inner {
        private Integer count;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}