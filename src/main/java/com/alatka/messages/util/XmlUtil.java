package com.alatka.messages.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class XmlUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new XmlFactory());

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
            throw new RuntimeException("获取xml文件错误", e);
        }
    }

    public static <T> Map<String, T> getMap(File file, Class<T> clazz) {
        return getMap(file, null, clazz);
    }

    public static <T> Map<String, T> getMap(File file, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerForMapOf(clazz).withRootName(rootName).readValue(file);
        } catch (IOException e) {
            throw new RuntimeException("获取xml文件错误: " + file.getName(), e);
        }
    }
}
