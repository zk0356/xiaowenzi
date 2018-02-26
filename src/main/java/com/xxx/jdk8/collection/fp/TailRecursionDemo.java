package com.xxx.jdk8.collection.fp;

/**
 * Created by xiaowenzi on 2018/2/26.
 */
public class TailRecursionDemo {

    /**
     * 尾递归
     * @param result
     * @param temp
     * @return
     */
    public static TailRecursion<Long> tailRecursion(final long result, final long temp) {
        if (temp == 1) {
            return TailInvoke.done(result);
        } else {
            return TailInvoke.call(() -> tailRecursion(result + temp, temp - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(tailRecursion(1, 10_000_000).invoke());
    }
}
