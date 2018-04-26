package com.xxx.jdk8.concurrent;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by xiaowenzi on 2018/4/18.
 */
public class ConcurrentDemo {
    public static void main(String[] args) {
//        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        int a = 100000,b = 20000;
        int i = a ^ b;
        System.out.println(i);
        int j = a % b;
        System.out.println(j);
//        ThreadLocal<String> local = new ThreadLocal<>();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        System.out.println(date + " " + time);
    }
}
