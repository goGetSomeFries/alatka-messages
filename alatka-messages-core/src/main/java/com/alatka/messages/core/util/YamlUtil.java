package com.alatka.messages.core.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T getObject(File file, Class<T> clazz) {
        return getObject(file, null, clazz);
    }

    public static <T> T getObject(File file, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerFor(clazz).withRootName(rootName).readValue(file);
        } catch (IOException e) {
            throw new RuntimeException("获取yaml文件错误", e);
        }
    }

    public static <T> Map<String, T> getMap(byte[] content, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerForMapOf(clazz).withRootName(rootName).readValue(content);
        } catch (IOException e) {
            throw new RuntimeException("获取yaml文件错误", e);
        }
    }
}
