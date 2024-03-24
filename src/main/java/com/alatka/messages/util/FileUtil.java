package com.alatka.messages.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author ybliu
 */
public class FileUtil {

    public static List<Path> getClasspathFiles(String classpath, String suffix) {
        return getClasspathFiles(classpath, suffix, FileUtil.class.getClassLoader());
    }

    public static List<Path> getClasspathFiles(String classpath, String suffix, ClassLoader classLoader) {
        List<Path> list = new ArrayList<>();
        try {
            URL url = classLoader.getResource(classpath);
            if (url == null) {
                throw new IllegalArgumentException("can not find classpath: " + classpath);
            }
            DirectoryStream<Path> paths =
                    Files.newDirectoryStream(Paths.get(url.toURI()), suffix);
            paths.forEach(list::add);
            return list;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
