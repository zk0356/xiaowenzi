package com.xxx.pattern.responsiblechain;

/**
 * Created by xiaowenzi on 2019/1/15.
 */
public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
