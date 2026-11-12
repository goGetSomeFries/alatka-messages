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
    @DisplayName("doDenormalizeType()")
    void test02() {
        byte[] bytes = BytesUtil.hexToBytes("2A561087AB346422");
        Bitmap bitmap = new Bitmap(bytes);
        FieldDefinition definition = new FieldDefinition();

        String expected = "F1~F8:00101010, F9~F16:01010110, F17~F24:00010000, F25~F32:10000111, F33~F40:10101011, F41~F48:00110100, F49~F56:01100100, F57~F64:00100010";
        Assertions.assertEquals(expected, fieldBuilder.doDenormalizeType(bitmap, definition));
    }

    @Test
    @DisplayName("returnIfNull()")
    void test03() {
        Assertions.assertFalse(fieldBuilder.returnIfNull());
    }

    @Test
    @DisplayName("normalizeType()")
    void test04() {
        FieldDefinition definition = new FieldDefinition();
        definition.setDomainNo(2);
        definition.setName("retCode");
        definition.setFixed(true);
        definition.setLength(6);
        definition.setRemark("响应码");
        definition.setClassType(String.class);

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fieldBuilder.normalizeType(new Object(), definition), definition + "无需指定，程序自动赋值");
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
