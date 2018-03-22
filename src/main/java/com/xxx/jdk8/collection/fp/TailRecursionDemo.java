package com.xxx.jdk8.collection.fp;

/**
 * Created by xiaowenzi on 2018/2/26.
 */
public class TailRecursionDemo {

    /**
     * 用lambda实现尾递归
     * @param result
     * @param temp
     * @return
     */
    public static TailRecursion<Long> tailRecursionWithLambda(final long result, final long temp) {
        if (temp == 1) {
            return TailInvoke.done(Long.valueOf(result));
        } else {
            return TailInvoke.call(() -> tailRecursionWithLambda(result + temp, temp - 1));
        }
    }

    /**
     * 传统的尾递归
     * @param result
     * @param temp
     * @return
     */
    public static long tailRecursionWithoutLambda(final long result, final long temp) {
        if (temp == 1) {
            return result;
        } else {
            return tailRecursionWithoutLambda(result + temp, temp - 1);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(tailRecursionWithLambda(1, 10_000).invoke().toString());
//        System.out.println(tailRecursionWithoutLambda(1, 10_000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间" + (end - start) + "毫秒");
    }
}
