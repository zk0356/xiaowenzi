package com.xxx.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static int i = 0;

    public static void main(String[] args) {
        Object lock = null;

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                print("t1");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                print("t2");
            }
        });

        t1.start();
        t2.start();
    }

    private static void print(String str) {
        logger.info("[" + str + "].i=" + (++i));
    }
}
