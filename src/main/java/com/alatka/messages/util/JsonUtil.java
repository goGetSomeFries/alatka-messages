package com.alatka.messages.util;

import com.alatka.messages.support.CustomPrettyPrinter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsBytes(map), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(Map<?, ?> map) {
        try {
            return OBJECT_MAPPER.writer(new CustomPrettyPrinter()).writeValueAsString(map);
        } catch (JsonProcessingException e) {
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
