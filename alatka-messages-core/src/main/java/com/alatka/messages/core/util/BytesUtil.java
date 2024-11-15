package com.alatka.messages.core.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ybliu
 */
public class BytesUtil {

    /**
     * bytes拼接
     *
     * @param bytesArray bytes数组
     * @return 拼接后bytes
     */
    public static byte[] concat(byte[]... bytesArray) {
        int length = Arrays.stream(bytesArray).mapToInt(bytes -> bytes.length).sum();
        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        Arrays.stream(bytesArray).forEach(byteBuffer::put);
        return byteBuffer.array();
    }

    public static byte[] binaryToBytes(String binaryStr) {
        if (binaryStr.length() % 8 != 0) {
            String prefix = IntStream.range(0, 8 - binaryStr.length() % 8)
                    .mapToObj(i -> "0")
                    .collect(Collectors.joining());
            binaryStr = prefix + binaryStr;
        }

        String result = binaryStr;
        Byte[] array = IntStream.range(0, binaryStr.length() / 8)
                .mapToObj(i -> result.substring(i * 8, i * 8 + 8))
                .map(s -> Integer.parseInt(s, 2))
                .map(Integer::byteValue)
                .toArray(Byte[]::new);
        byte[] bytes = new byte[binaryStr.length() / 8];
        IntStream.range(0, bytes.length)
                .forEach(i -> bytes[i] = array[i].byteValue());
        return bytes;
    }

    public static String bytesToBinary(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'))
                .collect(Collectors.joining());
    }

    public static byte[] hexToBytes(String hexStr) {
        int length = hexStr.length();
        if (length % 2 != 0) {
            hexStr = "0" + hexStr;
        }

        String result = hexStr;
        Byte[] array = IntStream.range(0, length / 2)
                .mapToObj(i -> result.substring(i * 2, i * 2 + 2))
                .map(s -> Integer.parseInt(s, 16))
                .map(Integer::byteValue)
                .toArray(Byte[]::new);
        byte[] bytes = new byte[length / 2];
        IntStream.range(0, bytes.length)
                .forEach(i -> bytes[i] = array[i].byteValue());
        return bytes;
    }

    public static String bytesToHex(byte[] bytes) {
        String result = new BigInteger(1, bytes).toString(16);
        String prefix = IntStream.range(0, bytes.length * 2 - result.length())
                .mapToObj(i -> "0")
                .collect(Collectors.joining());
        return prefix + result.toUpperCase();
    }

    public static byte[] intToBytes(int i) {
        String s = Integer.toBinaryString(i);
        return binaryToBytes(s);
    }

    public static int bytesToInt(byte[] bytes) {
        return new BigInteger(1, bytes).intValueExact();
    }

    public static byte[] toEBCDIC(String str) {
        return str.getBytes(Charset.forName("IBM-500"));
    }

    public static String fromEBCDIC(byte[] bytes) {
        return new String(bytes, Charset.forName("IBM-500"));
    }

    public static byte[] toBCD(String decimalStr) {
        decimalStr = decimalStr.length() % 2 == 0 ? decimalStr : "0".concat(decimalStr);
        byte[] bytes = decimalStr.getBytes();
        byte[] bcdBytes = new byte[bytes.length / 2];
        for (int i = 0; i < bcdBytes.length; i++) {
            bcdBytes[i] = (byte) (bytes[2 * i] << 4 | (bytes[2 * i + 1] & 0xf));
        }
        return bcdBytes;
    }

    public static String fromBCD(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(intToHexString(b >> 4 & 0xf)).append(intToHexString(b & 0xf));
        }
        return sb.toString();
    }

    private static String intToHexString(int i) {
        return Integer.toHexString(i).toUpperCase();
    }

}
