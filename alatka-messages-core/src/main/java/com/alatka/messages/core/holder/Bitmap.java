package com.alatka.messages.core.holder;

import com.alatka.messages.core.support.CustomJsonSerializer;
import com.alatka.messages.core.util.BytesUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonSerialize(using = CustomJsonSerializer.class)
public class Bitmap {

    private final byte[] bytes;

    private final int offset;

    public Bitmap(byte[] bytes) {
        this(bytes, 0);
    }

    public Bitmap(byte[] bytes, int offset) {
        this.bytes = bytes;
        this.offset = offset;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public boolean exist(int domainNo) {
        int result = domainNo - this.offset - 1;
        if (result / 8 >= this.bytes.length) {
            return false;
        }
        return (this.bytes[result / 8] & 0x80 >>> (result % 8)) == (0x80 >>> (result % 8));
    }

    @Override
    public String toString() {
        Map<String, String> map = new LinkedHashMap<>(this.bytes.length);
        for (int i = 0; i < this.bytes.length; i++) {
            String key = "F" + (i * 8 + offset + 1) + "~F" + (i * 8 + 8 + offset);
            String value = BytesUtil.bytesToBinary(Arrays.copyOfRange(bytes, i, i + 1));
            map.put(key, value);
        }
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(", ", "\"", "\""));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Bitmap bitmap = (Bitmap) object;
        return offset == bitmap.offset && Arrays.equals(bytes, bitmap.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(offset);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
