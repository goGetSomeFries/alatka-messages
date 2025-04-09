package com.alatka.messages.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileUtilTest {

    @Test
    @DisplayName("getClasspathFiles(String, String, ClassLoader) IllegalArgumentException")
    void test01() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> FileUtil.getFilesContent("/asdfghjkl", "", this.getClass().getClassLoader()));
    }

    @Test
    @DisplayName("getClasspathFiles(String, String, ClassLoader)")
    void test02() {
        String[] fileNames = {"a.log", "b.log"};
        List<byte[]> contents = FileUtil.getFilesContent("test", ".log", this.getClass().getClassLoader());
        Assertions.assertEquals(fileNames.length, contents.size());
    }

    @Disabled
    @Test
    @DisplayName("getClasspathFiles(String, String)")
    void test03() {
        // TODO
    }
}
