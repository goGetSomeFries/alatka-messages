package com.alatka.messages.core.holder;

import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BitmapTest {

    @Test
    @DisplayName("getBytes()")
    void test01() {
        byte[] bytes = BytesUtil.hexToBytes("2A56");
        Bitmap bitmap = new Bitmap(bytes);
        Assertions.assertArrayEquals(bytes, bitmap.getBytes());
    }

    @Test
    @DisplayName("toString()")
    void test02() {
        byte[] bytes = BytesUtil.hexToBytes("2A561087AB346422");
        Bitmap bitmap = new Bitmap(bytes);
        Assertions.assertEquals("\"F1~F8:00101010, F9~F16:01010110, F17~F24:00010000, F25~F32:10000111, F33~F40:10101011, F41~F48:00110100, F49~F56:01100100, F57~F64:00100010\"", bitmap.toString());
    }

    @Test
    @DisplayName("exist()")
    void test03() {
        byte[] bytes = BytesUtil.hexToBytes("2A");
        Bitmap bitmap = new Bitmap(bytes);

        Assertions.assertFalse(bitmap.exist(1));
        Assertions.assertFalse(bitmap.exist(2));
        Assertions.assertTrue(bitmap.exist(3));
        Assertions.assertFalse(bitmap.exist(4));
        Assertions.assertTrue(bitmap.exist(5));
        Assertions.assertFalse(bitmap.exist(6));
        Assertions.assertTrue(bitmap.exist(7));
        Assertions.assertFalse(bitmap.exist(8));
        Assertions.assertFalse(bitmap.exist(9));
    }

    @Test
    @DisplayName("exist()")
    void test04() {
        byte[] bytes = BytesUtil.hexToBytes("2A");
        Bitmap bitmap = new Bitmap(bytes, 13);

        Assertions.assertFalse(bitmap.exist(14));
        Assertions.assertFalse(bitmap.exist(15));
        Assertions.assertTrue(bitmap.exist(16));
        Assertions.assertFalse(bitmap.exist(17));
        Assertions.assertTrue(bitmap.exist(18));
        Assertions.assertFalse(bitmap.exist(19));
        Assertions.assertTrue(bitmap.exist(20));
        Assertions.assertFalse(bitmap.exist(21));
        Assertions.assertFalse(bitmap.exist(22));
    }

    @Disabled
    @Test
    @DisplayName("equals()")
    void test05() {
    }

    @Disabled
    @Test
    @DisplayName("hashcode()")
    void test06() {
    }
}
