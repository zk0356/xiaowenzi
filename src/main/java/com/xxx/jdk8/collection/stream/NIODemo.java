package com.xxx.jdk8.collection.stream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by xiaowenzi on 2018/2/9.
 */
public class NIODemo {
    public static void main(String[] args) {
        String fileName = "README.txt";
        final Path path = new File(fileName).toPath();
        try( Stream<String> lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
            lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
