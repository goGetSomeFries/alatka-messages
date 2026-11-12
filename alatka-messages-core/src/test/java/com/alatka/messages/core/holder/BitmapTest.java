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
