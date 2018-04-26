package com.xxx.jdk8.fp;

import com.xxx.jdk8.Nominator;

import java.util.Optional;

/**
 * Created by xiaowenzi on 2018/3/24.
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional.<Nominator>ofNullable(null)
                .filter(nominator -> nominator.getVote() > 30)
                .ifPresent(System.out::print);
    }
}
