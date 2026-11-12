package com.alatka.messages.core.holder;

import java.util.Arrays;
import java.util.Objects;

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
