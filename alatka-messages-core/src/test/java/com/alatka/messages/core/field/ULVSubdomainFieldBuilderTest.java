package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ULVSubdomainFieldBuilderTest {

    private ULVSubdomainFieldBuilder fieldBuilder = new ULVSubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.ULV);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("usageIdLength()")
    void test03() {
        Assertions.assertEquals(2, fieldBuilder.usageIdLength());
    }

    @Test
    @DisplayName("usageLenLength()")
    void test04() {
        Assertions.assertEquals(3, fieldBuilder.usageLenLength());
    }

    @Test
    @DisplayName("usageId(byte[])")
    void test05() {
        String str = "AA";
        Assertions.assertEquals(str, fieldBuilder.usageId(str.getBytes()));
    }

    @Test
    @DisplayName("usageLen(byte[])")
    void test06() {
        int len = 20;
        Assertions.assertEquals(len, fieldBuilder.usageLen(String.valueOf(len).getBytes()));
    }

    @Test
    @DisplayName("usageId(String)")
    void test07() {
        String str = "AA";
        Assertions.assertArrayEquals(str.getBytes(), fieldBuilder.usageId(str));
    }

    @Test
    @DisplayName("usageLen(int)")
    void test08() {
        int len = 20;
        Assertions.assertArrayEquals("020".getBytes(), fieldBuilder.usageLen(len));
    }
}
