package com.alatka.messages.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BytesUtil {

    public static byte[] concat(byte[]... bytesArray) {
        int length = Arrays.stream(bytesArray).mapToInt(bytes -> bytes.length).sum();
        ByteBuffer byteBuffer = ByteBuffer.allocate(length);
        Arrays.stream(bytesArray).forEach(byteBuffer::put);
        return byteBuffer.array();
    }

    public static String toString(byte[] bytes) {
        String binaryStr = new BigInteger(1, bytes).toString(2);
        // 左补0
        return padding(binaryStr);
    }

    public static byte[] toBytes(String binaryStr) {
        String result = padding(binaryStr);
        List<Byte> list = IntStream.range(0, result.length() / 8)
                .mapToObj(i -> result.substring(i * 8, (i + 1) * 8))
                .mapToInt(s -> Integer.parseInt(s, 2))
                // int强转byte，解决无符号byte问题
                .mapToObj(i -> (byte) i)
                .collect(Collectors.toList());
        byte[] bytes = new byte[list.size()];
        IntStream.range(0, list.size()).forEach(i -> bytes[i] = list.get(i));
        return bytes;
    }

    public static byte[] hexToBytes(String hexStr) {
        return new BigInteger(hexStr, 16).toByteArray();
    }

    public static String bytesToHex(byte[] bytes) {
        String str = new BigInteger(bytes).toString(16);
        return str.toUpperCase();
    }

    public static byte[] toBytes(int i) {
        String s = Integer.toBinaryString(i);
        return toBytes(s);
    }

    public static int toInt(byte[] bytes) {
        return Integer.parseInt(BytesUtil.toString(bytes), 2);
    }

    private static String padding(String str) {
        int count = str.length() % 8;
        return count == 0 ? str :
                IntStream.range(0, 8 - count).mapToObj(i -> "0").collect(Collectors.joining("")).concat(str);
    }

    public static byte[] toBCD(byte[] bytes) {
        byte[] bcdBytes = new byte[bytes.length / 2];
        for (int i = 0; i < bcdBytes.length; i++) {
            bcdBytes[i] = (byte) (bytes[2 * i] << 4 | (bytes[2 * i + 1] & 0xf));
        }
        return bcdBytes;
    }

    public static String fromBCD(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b >> 4 & 0xf).append(b & 0xf);
        }
        return sb.toString();
    }

}
