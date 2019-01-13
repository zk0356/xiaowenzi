package com.xxx.jdk8.nio;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by xiaowenzi on 2018/2/9.
 */
public class NIOFileDemo {
    public static void main(String[] args) {
//        testFilesLines();
//        testFilesList();
//        testFilesWalk();
        testPatternSplit();
    }

    private static void testFilesLines() {
        String fileName = ".gitignore";
        final Path path = new File(fileName).toPath();

        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose( () -> System.out.println("逐行扫描整个文件结束") ).forEach( System.out::println );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testPatternSplit() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("类路径为:\n" + path);
        Pattern.compile("/")
               .splitAsStream(path)
               .forEach(System.out::println);
    }

    private static void testFilesList() {
        String fileName = "学习资料(1).zip";
        String rootPath = ".";
        final Path path = new File(rootPath).toPath();

        try(Stream<Path> list = Files.list(path)) {
            list.filter(p -> p.getFileName().toString().equals(fileName))
                .onClose(() -> System.out.println("扫描当前工程根目录并找出指定的zip文件，打印出压缩文件列表"))
                .findFirst()
                .ifPresent(p -> {
                    System.out.println("找到文件" + p.getFileName().toString());

                    try(ZipFile zip = new ZipFile(p.getFileName().toString(), Charset.forName("GBK"))) {
                        final String keyword = "绕过这个环";
                        Predicate<ZipEntry> isFile = entry -> !entry.isDirectory();
                        Predicate<ZipEntry> containKeywords = entry -> {
                            boolean contain = false;

                            try(InputStream inputStream = zip.getInputStream(entry);
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                Stream<String> lines = reader.lines()) {
                                contain = lines.filter(line -> line.contains(keyword))
                                               .findFirst()
                                               .isPresent();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (contain) {
                                System.out.println("找到包含关键字["+keyword+"]的文件["+entry.getName()+"]");
                            } else {
                                System.out.println("文件["+entry.getName()+"]未包含关键字["+keyword+"]");
                            }

                            return contain;
                        };
                        zip.stream()
                           .filter(isFile.and(containKeywords))
                           .forEach(entry -> System.out.println(p.getFileName().toString() + ":[" + entry.getName() + "]"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFilesWalk() {
        String fileName = "src";
        final Path path = new File(fileName).toPath();

        try(Stream<Path> list = Files.walk(path)) {
            final String keyword = "@FunctionalInterface";
            Predicate<File> isFile = f -> !f.isDirectory();
            Predicate<File> containKeyword = f -> {
                boolean contain = false;

                try(FileInputStream fr = new FileInputStream(f.getAbsoluteFile());
                    InputStreamReader is = new InputStreamReader(fr,"UTF-8");
                    BufferedReader reader = new BufferedReader(is);
                    Stream<String> lines = reader.lines()) {
                    contain = lines.filter(line -> line.contains(keyword))
                                   .findFirst()
                                   .isPresent();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!contain) {
                    System.out.println("文件["+f.getName()+"]未包含关键字");
                }

                return contain;
            };
            list.map(Path::toFile)
                .filter(isFile.and(containKeyword))
                .forEach(file -> System.out.println("找到包含关键字["+keyword+"]的文件["+file.getName()+"]"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
