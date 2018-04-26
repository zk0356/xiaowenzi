package com.xxx.jdk8.stream;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by xiaowenzi on 2017/9/5.
 */
public class CompletableFutureDemo {
    private static Logger logger = Logger.getLogger("DemoLogger");
    public static void main(String[] args) {
//        IntStream input = IntStream.rangeClosed(1, 100000).filter(value -> value % 7 == 0);
//        CompletableFuture.supplyAsync(() -> input.limit(100).sum())
//                         .whenComplete((result, throwable) -> out.println(result))
//                         .join();
        CompletableFuture[] futures = Stream.of("A01", "A02", "A03", "A04", "A05", "A06", "B01", "B06", "B07", "C01", "C02")
                                            .map(product -> CompletableFuture.runAsync(() -> {
                                                                WithdrawCollector.getInstance().fetchFromWs(product);
                                                                WithdrawCollector.getInstance().sendWaitingToKafka(product);
                                                                WithdrawCollector.getInstance().sendRetryToKafka(product);
                                                            }).whenComplete((aVoid, throwable) -> {
                                                            if (throwable != null) {
                                                                logger.log(Level.INFO, product + "线程执行异常", throwable);
                                                                if (throwable instanceof InterruptedException) {
                                                                    System.out.println(product + "线程被中断");
                                                                }
                                                                if (throwable instanceof CancellationException) {
                                                                    System.out.println(product + "线程被取消");
                                                                }
                                                            }
                                                        })
                                            )
                                            .toArray(CompletableFuture[]::new);
//        testAllOf(futures);
        testCancel(futures);
    }

    private static void testCancel(CompletableFuture[] futures) {
        for (CompletableFuture future: futures) {
            if (!future.isDone()) {
                try {
//                    Thread.sleep(100);
                    future.cancel(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testAllOf(CompletableFuture[] futures) {
        try {
            CompletableFuture.allOf(futures)
                             .whenComplete((aVoid, throwable) -> {
                                 if (throwable != null && throwable instanceof RuntimeException) {
                                     System.out.println("线程池捕获一个运行时异常");
                                 }
                             })
                             .get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "线程池中断异常", e);
        } catch (ExecutionException e) {
            logger.log(Level.INFO, "线程池执行异常", e);
        } catch (TimeoutException e) {
            logger.log(Level.INFO, "线程执行超时", e);
        }
    }
}
