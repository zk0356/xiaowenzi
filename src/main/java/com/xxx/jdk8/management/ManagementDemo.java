package com.xxx.jdk8.management;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * Created by xiaowenzi on 2018/4/26.
 */
public class ManagementDemo {
    public static void main(String[] args) {
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println("java堆内存大小为：" + heapMemoryUsage.toString());
        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println("java非堆内存大小为：" + nonHeapMemoryUsage.toString());
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
            System.out.println("\n" + memoryPoolMXBean.getName());
            System.out.println(memoryPoolMXBean.getType());
            System.out.println(memoryPoolMXBean.getUsage());
            System.out.println(memoryPoolMXBean.getPeakUsage());
            System.out.println(memoryPoolMXBean.getCollectionUsage());
        }
    }
}
