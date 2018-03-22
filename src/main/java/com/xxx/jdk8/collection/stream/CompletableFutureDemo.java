package com.xxx.jdk8.collection.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaowenzi on 2017/9/5.
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
//        IntStream input = IntStream.rangeClosed(1, 100000).filter(value -> value % 7 == 0);
//        CompletableFuture.supplyAsync(() -> input.limit(100).sum())
//                         .whenComplete((result, throwable) -> out.println(result))
//                         .join();
        List<String> input = Stream.of("A01","A02","A03","A04","A05","A06","B01","B06","B07","C01","C02")
                                   .collect(Collectors.toList());
        List<CompletableFuture> futureList = new ArrayList<>();
        input.forEach(product -> futureList.add(CompletableFuture.runAsync(() -> {
            WithdrawCollector.getInstance().fetchFromWs(product);
            WithdrawCollector.getInstance().sendWaitingToKafka(product);
            WithdrawCollector.getInstance().sendRetryToKafka(product);
        })));
        try {
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
                             .whenComplete((aVoid, throwable) -> {
                                 if (throwable != null) {
                                     if (throwable instanceof RuntimeException) {
                                         System.out.println("线程池捕获一个运行时异常");
                                     }
                                 }
                             }).get();
        } catch (InterruptedException e) {
            System.out.println("线程池中断异常");
        } catch (ExecutionException e) {
            System.out.println("线程池执行异常");
        }
    }
}
