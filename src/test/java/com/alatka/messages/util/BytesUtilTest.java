package com.alatka.messages.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BytesUtilTest {

    @Test
    void test01() {
        byte[] bytes = BytesUtil.hexToBytes("01");
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("00000001", s);
    }

    @Test
    void test02() {
        byte[] bytes = BytesUtil.hexToBytes("00");
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("00000000", s);
    }

    @Test
    void test03() {
        byte[] bytes = new byte[]{0, 0};
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("0000000000000000", s);
    }

    @Test
    void test04() {
        byte[] bytes = BytesUtil.hexToBytes("80");
        System.out.println(Integer.toBinaryString(bytes[0]));
        System.out.println(bytes[0]);
        System.out.println(bytes[0] & 0xFF);
        System.out.println(Integer.toBinaryString(bytes[0] & 0xFF));
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("0000000000000000", s);
    }
}
