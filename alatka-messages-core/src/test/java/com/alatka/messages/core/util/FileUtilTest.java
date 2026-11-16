package com.alatka.messages.core.util;

import com.alatka.messages.core.support.FileWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUtilTest {

    @Test
    @DisplayName("getClasspathFilesContent(String, String, ClassLoader) IllegalArgumentException")
    void test01() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> FileUtil.getClasspathFilesContent("/asdfghjkl", "", this.getClass().getClassLoader()));
    }

    @Test
    @DisplayName("getClasspathFilesContent(String, String, ClassLoader)")
    void test02() {
        String[] fileNames = {"a.log", "b.log"};
        List<FileWrapper> fileWrappers = FileUtil.getClasspathFilesContent("test", ".log", this.getClass().getClassLoader());
        Assertions.assertEquals(fileNames.length, fileWrappers.size());

        AtomicInteger index = new AtomicInteger(0);
        fileWrappers.stream()
                .map(FileWrapper::getName)
                .sorted()
                .forEach(name -> Assertions.assertEquals(fileNames[index.getAndIncrement()], name));

    }

    @Disabled
    @Test
    @DisplayName("getFileSystemFilesContent(String, String)")
    void test03() {
        System.out.println(System.getProperty("user.dir"));
        String[] fileNames = {"a.log", "b.log"};
        List<FileWrapper> fileWrappers = FileUtil.getFileSystemFilesContent("target/test-classes/test/", ".log");
        AtomicInteger index = new AtomicInteger(0);
        fileWrappers.stream()
                .map(FileWrapper::getName)
                .sorted()
                .forEach(name -> Assertions.assertEquals(fileNames[index.getAndIncrement()], name));

    }
}
