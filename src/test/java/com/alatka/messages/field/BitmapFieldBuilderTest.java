package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BitmapFieldBuilderTest {

    private BitmapFieldBuilder fieldBuilder = new BitmapFieldBuilder();

    @Test
    @DisplayName("order() == 60")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(60, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(Map.class);
        fieldDefinition.setDomainNo(1);
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setType(MessageDefinition.Type.iso);
        messageDefinition.setKind(MessageDefinition.Kind.payload);
        Assertions.assertTrue(fieldBuilder.matched(messageDefinition, fieldDefinition));
    }

    @Test
    @DisplayName("returnIfNull()")
    void test03() {
        Assertions.assertFalse(fieldBuilder.returnIfNull());
    }

    @Test
    @DisplayName("generate()")
    void test04() {
        String hex = "2F";
        Map<Integer, Boolean> map = BitmapFieldBuilder.generate(BytesUtil.hexToBytes(hex));
        Assertions.assertFalse(map.get(1));
        Assertions.assertFalse(map.get(2));
        Assertions.assertTrue(map.get(3));
        Assertions.assertFalse(map.get(4));
        Assertions.assertTrue(map.get(5));
        Assertions.assertTrue(map.get(6));
        Assertions.assertTrue(map.get(7));
        Assertions.assertTrue(map.get(8));
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test05() {
        String hex = "3E";
        Map<Integer, Boolean> map = fieldBuilder.toObjectWithBinary(BytesUtil.hexToBytes(hex), null);
        Assertions.assertFalse(map.get(1));
        Assertions.assertFalse(map.get(2));
        Assertions.assertTrue(map.get(3));
        Assertions.assertTrue(map.get(4));
        Assertions.assertTrue(map.get(5));
        Assertions.assertTrue(map.get(6));
        Assertions.assertTrue(map.get(7));
        Assertions.assertFalse(map.get(8));
    }

    @Test
    @DisplayName("fromObjectToBinary() bitmap = null")
    void test06() {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, false);
        map.put(2, false);
        map.put(3, true);
        map.put(4, false);
        map.put(5, false);
        map.put(6, true);
        map.put(7, false);
        map.put(8, true);
        byte[] bytes = fieldBuilder.fromObjectToBinary(map, null);
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "25");
    }

    // TODO
    @Disabled
    @Test
    @DisplayName("fromObjectToBinary() bitmap != null")
    void test07() {
        MessageDefinition messageDefinition = new MessageDefinition();
        MessageDefinitionContext.getInstance().fieldDefinitions(messageDefinition, Collections.emptyList());

        fieldBuilder.setMessageDefinition(messageDefinition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);
        fieldDefinition.setLength(8);
        byte[] bytes = fieldBuilder.fromObjectToBinary(null, fieldDefinition);
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "0");
    }
}
