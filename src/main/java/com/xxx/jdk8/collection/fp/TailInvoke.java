package com.xxx.jdk8.collection.fp;

/**
 * 使用尾递归的包装类
 * Created by xiaowenzi on 2018/2/26.
 */
public class TailInvoke {
    public static <T> TailRecursion<T> call(final TailRecursion<T> nextFrame) {
        return nextFrame;
    }

    public static <T> TailRecursion<T> done(T value) {
        return new TailRecursion<T>() {
            @Override
            public TailRecursion<T> apply() {
                throw new Error("递归已结束,非法调用apply方法");
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public T getResult() {
                return value;
            }
        };
    }
}
