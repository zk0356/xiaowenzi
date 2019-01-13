package com.xxx.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaowenzi on 2017/8/17.
 */
public class ConcurrentHashMapDemo {
    private static Logger logger = LoggerFactory.getLogger(ConcurrentHashMapDemo.class);

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.merge("judd", 42, (oldValue, newValue) -> oldValue + newValue);
        map.merge("wason", 35, (oldValue, newValue) -> oldValue + newValue);
        map.merge("bond", 89, (oldValue, newValue) -> oldValue + newValue);
        map.merge("sigmond", 51, (oldValue, newValue) -> oldValue + newValue);
        map.merge("fenix", 173, (oldValue, newValue) -> oldValue + newValue);
        map.merge("kaka", 9, (oldValue, newValue) -> oldValue + newValue);
        map.merge("zak", 126, (oldValue, newValue) -> oldValue + newValue);

        String search = map.search(1, (key, value) -> value % 8 == 0 ? key : null);
        logger.info(search);
        map.forEachKey(1, key -> "网站开发部:" + key, logger::info);
        map.forEach(1, (key, value) -> value % 7 == 0 ? key : null, logger::info);
        Integer reduce = map.reduce(1, (key, value) -> value % 7 == 0 ? value : null, (v1, v2) -> v1 + v2);
        logger.info("reduce=" + reduce);
        int sum = map.reduceValuesToInt(1, Integer::intValue, 0, (left, right) -> left + right);
        logger.info("sum=" + sum);
    }
}
