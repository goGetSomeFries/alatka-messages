package com.alatka.messages.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

public class FileUtilTest {

    @Test
    public void test01() {
        List<Path> list = FileUtil.getClasspathFiles("test", "*.test");
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void test02() {
        List<Path> list = FileUtil.getClasspathFiles("", "*.test");
        Assertions.assertEquals(1, list.size());
    }
}
