package com.alatka.messages.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUtilTest {

    @Test
    @DisplayName("getClasspathFiles(String, String, ClassLoader) IllegalArgumentException")
    void test01() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> FileUtil.getClasspathFiles("/asdfghjkl", "", this.getClass().getClassLoader()));
    }

    @Test
    @DisplayName("getClasspathFiles(String, String, ClassLoader)")
    void test02() {
        String[] fileNames = {"a.log", "b.log"};
        List<Path> paths = FileUtil.getClasspathFiles("test", "*.log", this.getClass().getClassLoader());
        Assertions.assertEquals(fileNames.length, paths.size());

        AtomicInteger index = new AtomicInteger(0);
        paths.stream()
                .map(path -> path.toFile().getName())
                .sorted()
                .forEach(name -> Assertions.assertEquals(fileNames[index.getAndIncrement()], name));
    }

    @Disabled
    @Test
    @DisplayName("getClasspathFiles(String, String)")
    void test03() {
        // TODO
    }
}
