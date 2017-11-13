package com.xxx.jdk8.collection.stream;

import com.xxx.jdk8.collection.Nominator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaowenzi on 2017/9/27.
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Nominator> nominatorList = new ArrayList<>();
        nominatorList.add(new Nominator("zak",3));
        nominatorList.add(new Nominator("judd",4));
        nominatorList.add(new Nominator("bond",1));
        nominatorList.add(new Nominator("zak",2));
        nominatorList.add(new Nominator("wason",5));
        nominatorList.parallelStream().distinct().collect(Collectors.toList()).forEach(System.out::println);
        nominatorList.stream().filter(Nominator::interview).collect(Collectors.groupingBy(Nominator::getName));
//        nominatorList
//                .stream()
//                .collect(Collectors.groupingBy(Nominator::getVote))
//                .entrySet()
//                .stream()
//                .filter(integerListEntry -> integerListEntry.getValue().size() > 1)
//                .map(integerListEntry -> integerListEntry.getValue().stream().map(Nominator::getName).collect(Collectors.toList()))
//                .forEach(System.out::println);
    }
}
