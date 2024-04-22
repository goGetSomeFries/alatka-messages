package com.alatka.messages.util;

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
        return toBytes(binaryStr, 2);
    }

    public static String bytesToBinary(byte[] bytes) {
        String binaryStr = new BigInteger(1, bytes).toString(2);
        // 左补0
        return padding(binaryStr);
    }

    public static byte[] hexToBytes(String hexStr) {
        return toBytes(hexStr, 16);
    }

    public static String bytesToHex(byte[] bytes) {
        String str = new BigInteger(1, bytes).toString(16);
        return str.length() % 2 == 0 ? str.toUpperCase() : "0".concat(str.toUpperCase());
    }

    public static byte[] intToBytes(int i) {
        String s = Integer.toBinaryString(i);
        return binaryToBytes(s);
    }

    public static int bytesToInt(byte[] bytes) {
        return new BigInteger(1, bytes).intValueExact();
    }

    public static byte[] toEBCDIC(byte[] bytes) {
        return new String(bytes).getBytes(Charset.forName("IBM-500"));
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

    private static String padding(String str) {
        int count = str.length() % 8;
        return count == 0 ? str :
                IntStream.range(0, 8 - count).mapToObj(i -> "0").collect(Collectors.joining("")).concat(str);
    }

    private static byte[] toBytes(String str, int radix) {
        // TODO bug str = "0011"时，bytes.length = 1
        byte[] bytes = new BigInteger(str, radix).toByteArray();
        if (bytes.length > 1 && bytes[0] == 0 && (bytes[1] & 0x80) == 0x80) {
            return Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return bytes;
    }
}
