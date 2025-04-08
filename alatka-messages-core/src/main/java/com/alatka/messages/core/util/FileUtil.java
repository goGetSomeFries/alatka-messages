package com.alatka.messages.core.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<Path> getClasspathFiles(String classpath, String suffix) {
        return getClasspathFiles_temp(classpath, suffix, FileUtil.class.getClassLoader());
//        return getClasspathFiles(classpath, suffix, FileUtil.class.getClassLoader());
    }

    public static List<Path> getClasspathFiles_v1(String classpath, String suffix) {

        try {
            Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(null)
                    .getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + classpath + (classpath.endsWith("/") ? "*" : "/*") + suffix);
            return Arrays.stream(resources)
                    .map(resource -> {
                        try {
                            return Paths.get(resource.getURI());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Path> getClasspathFiles(String classpath, String suffix, ClassLoader classLoader) {
        try {
            URL url = classLoader.getResource(classpath);
            if (url == null) {
                throw new IllegalArgumentException("can not find classpath: " + classpath);
            }

            List<Path> list = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(url.toURI()), "*" + suffix)) {
                stream.forEach(list::add);
            }
            return list;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Path> getClasspathFiles_temp(String classpath, String suffix, ClassLoader classLoader) {
        URL url = classLoader.getResource(classpath);
        if (url == null) {
            throw new IllegalArgumentException("can not find classpath: " + classpath);
        }

        try {
            if (url.toURI().getScheme().equals("file")) {
                return buildPaths(Paths.get(url.toURI()), suffix);
            }
            if (url.toURI().getScheme().equals("jar")) {
                try (FileSystem fs = FileSystems.newFileSystem(url.toURI(), Collections.emptyMap(), classLoader)) {
                    Path path = Files.exists(fs.getPath("/BOOT-INF/classes")) && !classpath.startsWith("META-INF") ?
                            fs.getPath("/BOOT-INF/classes", classpath) : fs.getPath(classpath);
                    return buildPaths(path, suffix);
                }
            }
            throw new IllegalArgumentException("illegal scheme, classpath: " + classpath);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Path> buildPaths(Path path, String suffix) throws IOException {
        try (Stream<Path> stream = Files.walk(path)) {
            return stream.filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(suffix))
                    .collect(Collectors.toList());
        }
    }

}
