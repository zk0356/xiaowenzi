package com.xxx.jdk8.collection.stream;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * Created by xiaowenzi on 2017/9/5.
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        IntStream input = IntStream.rangeClosed(1, 100000).filter(value -> value % 7 == 0);
        CompletableFuture.supplyAsync(() -> input.limit(100).sum())
                         .whenComplete((result, throwable) -> out.println(result))
                         .join();
    }
}
