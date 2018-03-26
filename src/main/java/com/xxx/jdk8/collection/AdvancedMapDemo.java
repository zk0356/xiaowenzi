package com.xxx.jdk8.collection;

import java.util.EnumMap;

/**
 * Created by xiaowenzi on 2018/3/23.
 */
public class AdvancedMapDemo {
    public static void main(String[] args) {
        EnumMap<MathFunctions, String> enumMap = new EnumMap<>(MathFunctions.class);
        enumMap.put(MathFunctions.SUM, "加");
        enumMap.put(MathFunctions.SUBSTRACT, "减");
        enumMap.put(MathFunctions.MULTIPLY, "乘");
        enumMap.put(MathFunctions.DIVIDE, "除");
    }
}
