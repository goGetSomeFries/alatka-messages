package com.alatka.messages.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class ObjectUtil {

    public static <K, V> boolean equals(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == map2) {
            return true;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        try {
            Iterator<Map.Entry<K, V>> i = map1.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<K, V> e = i.next();
                K key = e.getKey();
                Object value = e.getValue();
                if (value == null) {
                    if (!(map2.get(key) == null && map2.containsKey(key))) {
                        return false;
                    }
                } else {
                    if (value instanceof byte[]) {
                        if (!Arrays.equals((byte[]) value, (byte[]) map2.get(key))) {
                            return false;
                        }
                    } else {
                        if (!value.equals(map2.get(key))) {
                            return false;
                        }
                    }
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }
}
