package com.alatka.messages.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsBytes(map), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> buildList(byte[] bytes, Class<T> clazz) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
            return OBJECT_MAPPER.readValue(bytes, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
