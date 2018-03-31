package com.xxx.jdk8.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaowenzi on 2017/8/17.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.merge("judd", 42, (oldValue, newValue) -> oldValue + newValue);
        map.merge("wason", 35, (oldValue, newValue) -> oldValue + newValue);
        map.merge("bond", 89, (oldValue, newValue) -> oldValue + newValue);
        map.merge("sigmond", 51, (oldValue, newValue) -> oldValue + newValue);
        map.merge("fenix", 173, (oldValue, newValue) -> oldValue + newValue);
        map.merge("kaka", 9, (oldValue, newValue) -> oldValue + newValue);

        map.merge("zak", 126, (oldValue, newValue) -> oldValue + newValue);
//        System.out.println(map.get("zak"));
//        map.merge("zak", 32, (oldValue, newValue) -> oldValue + newValue);
//        System.out.println(map.get("zak"));
//        map.merge("zak", 32, (oldValue, newValue) -> null);
//        System.out.println(map.get("zak"));
//        map.merge("zak", 32, (oldValue, newValue) -> oldValue + newValue);
//        System.out.println(map.get("zak"));

        String search = map.search(1, (key, value) -> value % 8 == 0 ? key : null);
        System.out.println(search);
//        map.forEach((key, value) -> System.out.println(key+":"+value));
        map.forEachKey(1, key -> "网站开发部:" + key, System.out::println);
        map.forEach(1, (key, value) -> value % 7 == 0 ? key : null, System.out::println);
        Integer reduce = map.reduce(1, (key, value) -> value % 7 == 0 ? value : null, (v1, v2) -> v1 + v2);
        System.out.println(reduce);
        int i = map.reduceValuesToInt(1, Integer::intValue, 0, (left, right) -> left + right);
        System.out.println(i);
    }
}
