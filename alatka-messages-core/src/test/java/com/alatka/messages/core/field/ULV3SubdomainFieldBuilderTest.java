package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ULV3SubdomainFieldBuilderTest {

    private ULV3SubdomainFieldBuilder fieldBuilder = new ULV3SubdomainFieldBuilder();

    @Test
    @DisplayName("order() == 100")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(100, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setExistSubdomain(true);
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.ULV3);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("usageIdLength()")
    void test03() {
        Assertions.assertEquals(1, fieldBuilder.usageIdLength());
    }

    @Test
    @DisplayName("usageLenLength()")
    void test04() {
        Assertions.assertEquals(2, fieldBuilder.usageLenLength());
    }

    @Test
    @DisplayName("usageId(byte[])")
    void test05() {
        String hex = "1A";
        Assertions.assertEquals("1A", fieldBuilder.usageId(BytesUtil.hexToBytes(hex)));
    }

    @Test
    @DisplayName("usageLen(byte[])")
    void test06() {
        String hex = "10";
        Assertions.assertEquals(16, fieldBuilder.usageLen(BytesUtil.hexToBytes(hex)));
    }

    @Test
    @DisplayName("usageId(String)")
    void test07() {
        String str = "01";
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("01"), fieldBuilder.usageId(str));
    }

    @Test
    @DisplayName("usageLen(int)")
    void test08() {
        int len = 2;
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("0002"), fieldBuilder.usageLen(len));
    }

    @Test
    @DisplayName("usageLen(int)")
    void test09() {
        int len = 256;
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("0100"), fieldBuilder.usageLen(len));
    }
}
