package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.holder.Bitmap;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class AbstractBitmapFieldBuilderTest {

    private BitmapFieldBuilder fieldBuilder = new BitmapFieldBuilder();

    @Test
    @DisplayName("order() == 60")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(60, order);
    }

    @Test
    @DisplayName("returnIfNull()")
    void test03() {
        Assertions.assertFalse(fieldBuilder.returnIfNull());
    }

    @Test
    @DisplayName("toObjectWithBinary()")
    void test05() {
        String hex = "3E";
        Bitmap bitmap = fieldBuilder.toObjectWithBinary(BytesUtil.hexToBytes(hex), new FieldDefinition());
        Assertions.assertArrayEquals(BytesUtil.hexToBytes(hex), bitmap.getBytes());
    }

    @Test
    @DisplayName("fromObjectToBinary() bitmap != null")
    void test06() {
        Bitmap bitmap = new Bitmap(BytesUtil.hexToBytes("25"));
        byte[] bytes = fieldBuilder.fromObjectToBinary(bitmap, null);
        Assertions.assertEquals(BytesUtil.bytesToHex(bytes), "25");
    }

    // TODO
    @Disabled
    @Test
    @DisplayName("fromObjectToBinary() bitmap = null")
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
