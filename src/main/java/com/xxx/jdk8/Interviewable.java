package com.xxx.jdk8;

/**
 * Created by xiaowenzi on 2017/11/6.
 */
public interface Interviewable {
    static boolean interview(Nominator nominator) {
        return nominator.getVote() > 5 ? true : false;
    }
    default boolean offer(Nominator nominator) {
        return nominator.getName().equals("zak") ? true : false;
    }
}
