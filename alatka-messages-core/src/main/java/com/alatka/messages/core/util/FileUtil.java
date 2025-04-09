package com.alatka.messages.core.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件工具类
 *
 * @author ybliu
 */
public class FileUtil {

    public static List<byte[]> getFilesContent(String classpath, String suffix) {
        return getFilesContent(classpath, suffix, FileUtil.class.getClassLoader());
    }

    public static List<byte[]> getFilesContent(String classpath, String suffix, ClassLoader classLoader) {
        URL url = classLoader.getResource(classpath);
        if (url == null) {
            throw new IllegalArgumentException("can not find classpath: " + classpath);
        }

        try {
            if (url.toURI().getScheme().equals("file")) {
                return buildFilesContent(Paths.get(url.toURI()), suffix);
            }

            if (url.toURI().getScheme().equals("jar")) {
                try (FileSystem fs = FileSystems.newFileSystem(url.toURI(), Collections.emptyMap(), classLoader)) {
                    // springboot '/BOOT-INF/classes'
                    Path path = Files.exists(fs.getPath("/BOOT-INF/classes")) && !classpath.startsWith("META-INF") ?
                            fs.getPath("/BOOT-INF/classes", classpath) : fs.getPath(classpath);
                    return buildFilesContent(path, suffix);
                }
            }
            throw new IllegalArgumentException("illegal scheme, classpath: " + classpath);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<byte[]> buildFilesContent(Path path, String suffix) throws IOException {
        try (Stream<Path> stream = Files.list(path)) {
            return stream.filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(suffix))
                    .map(p -> {
                        try {
                            return Files.readAllBytes(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

}
