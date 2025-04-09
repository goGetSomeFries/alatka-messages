package com.alatka.messages.core.util;

import com.alatka.messages.core.holder.FileWrapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;

import java.io.IOException;
import java.util.Map;

public class XmlUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new XmlFactory());

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> Map<String, T> getMap(FileWrapper fileWrapper, Class<T> clazz) {
        return getMap(fileWrapper, null, clazz);
    }

    public static <T> Map<String, T> getMap(FileWrapper fileWrapper, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerForMapOf(clazz).withRootName(rootName).readValue(fileWrapper.getContent());
        } catch (IOException e) {
            throw new RuntimeException("获取yaml文件错误: " + fileWrapper.getName(), e);
        }
    }
}
