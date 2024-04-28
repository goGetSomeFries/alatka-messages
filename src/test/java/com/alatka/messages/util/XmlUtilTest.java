package com.alatka.messages.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class XmlUtilTest {

    @Test
    void test01() {
        List<Path> list = FileUtil.getClasspathFiles("", "*.iso.xml");
        Map<String, Object> map = XmlUtil.getMap(list.get(0).toFile(), null, Object.class);
        System.out.println(map);
    }
}
