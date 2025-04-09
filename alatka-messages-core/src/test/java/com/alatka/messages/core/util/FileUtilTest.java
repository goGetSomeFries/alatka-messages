package com.alatka.messages.core.util;

import com.alatka.messages.core.holder.FileWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<FileWrapper> fileWrappers = FileUtil.getFilesContent("test", ".log", this.getClass().getClassLoader());
        Assertions.assertEquals(fileNames.length, fileWrappers.size());

        AtomicInteger index = new AtomicInteger(0);
        fileWrappers.stream()
                .map(FileWrapper::getFileName)
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
