package com.xxx.jdk8.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by xiaowenzi on 2017/9/5.
 */
public class CompletableFutureDemo {
    private static Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

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
                                                                logger.info(product + "线程执行异常", throwable);

                                                                if (throwable instanceof InterruptedException) {
                                                                    logger.info(product + "线程被中断");
                                                                }

                                                                if (throwable instanceof CancellationException) {
                                                                    logger.info(product + "线程被取消");
                                                                }
                                                            }
                                                            else {
                                                                logger.info(product + "线程执行结束");
                                                            }
                                                        })
                                            )
                                            .toArray(CompletableFuture[]::new);
        testAllOf(futures);
//        testCancel(futures);
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
        CompletableFuture.allOf(futures)
                         .join();
    }
}
