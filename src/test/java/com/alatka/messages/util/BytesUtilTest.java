package com.alatka.messages.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class BytesUtilTest {

    @Test
    void test01() {
        byte[] bytes = new byte[]{1};
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("00000001", s);
    }

    @Test
    void test02() {
        byte[] bytes = new byte[]{0};
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
        byte[] bytes = new byte[]{0, 2};
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("0000000000000010", s);
    }

    @Test
    void test05() {
        byte[] bytes = new byte[]{-128};
        String s = BytesUtil.bytesToBinary(bytes);
        Assertions.assertEquals("10000000", s);
    }

    @Test
    void test06() {
        byte[] bytes = BytesUtil.binaryToBytes("00000001");
        Assertions.assertArrayEquals(new byte[]{1}, bytes);
    }

    @Test
    void test07() {
        byte[] bytes = BytesUtil.binaryToBytes("00000000");
        Assertions.assertArrayEquals(new byte[]{0}, bytes);
    }

    @Test
    void test08() {
        byte[] bytes = BytesUtil.binaryToBytes("0000000000000000");
        Assertions.assertArrayEquals(new byte[]{0, 0}, bytes);
    }

    @Test
    void test09() {
        byte[] bytes = BytesUtil.binaryToBytes("0000000000000010");
        Assertions.assertArrayEquals(new byte[]{0, 2}, bytes);
    }

    @Test
    void test10() {
        byte[] bytes = BytesUtil.binaryToBytes("10000000");
        Assertions.assertArrayEquals(new byte[]{-128}, bytes);
    }

    @Test
    void test11() {
        byte[] bytes = new byte[]{1};
        String s = BytesUtil.bytesToHex(bytes);
        Assertions.assertEquals("01", s);
    }

    @Test
    void test12() {
        byte[] bytes = new byte[]{0};
        String s = BytesUtil.bytesToHex(bytes);
        Assertions.assertEquals("00", s);
    }

    @Test
    void test13() {
        byte[] bytes = new byte[]{0, 0};
        String s = BytesUtil.bytesToHex(bytes);
        Assertions.assertEquals("0000", s);
    }

    @Test
    void test14() {
        byte[] bytes = new byte[]{0, 2};
        String s = BytesUtil.bytesToHex(bytes);
        Assertions.assertEquals("0002", s);
    }

    @Test
    void test15() {
        byte[] bytes = new byte[]{-128};
        String s = BytesUtil.bytesToHex(bytes);
        Assertions.assertEquals("80", s);
    }

    @Test
    void test16() {
        byte[] bytes = BytesUtil.hexToBytes("01");
        Assertions.assertArrayEquals(new byte[]{1}, bytes);
    }

    @Test
    void test17() {
        byte[] bytes = BytesUtil.hexToBytes("00");
        Assertions.assertArrayEquals(new byte[]{0}, bytes);
    }

    @Test
    void test18() {
        byte[] bytes = BytesUtil.hexToBytes("0000");
        Assertions.assertArrayEquals(new byte[]{0, 0}, bytes);
    }

    @Test
    void test19() {
        byte[] bytes = BytesUtil.hexToBytes("0002");
        Assertions.assertArrayEquals(new byte[]{0, 2}, bytes);
    }

    @Test
    void test20() {
        byte[] bytes = BytesUtil.hexToBytes("80");
        Assertions.assertArrayEquals(new byte[]{-128}, bytes);
    }

    @Test
    void test99() {
        String hexStr = "028010";
        Byte[] array = IntStream.range(0, hexStr.length() / 2)
                .mapToObj(i -> hexStr.substring(i * 2, i * 2 + 2))
                .map(s -> Integer.parseInt(s, 16))
                .map(Integer::byteValue)
                .toArray(Byte[]::new);
        byte[] bytes = new byte[hexStr.length() / 2];
        IntStream.range(0, bytes.length)
                .forEach(i -> bytes[i] = array[i].byteValue());
        String str = new BigInteger(1, bytes).toString(16);
        System.out.println(str);
        System.out.println(BytesUtil.bytesToHex(bytes));
    }
}
