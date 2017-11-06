package com.xxx.jdk8.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xiaowenzi on 2017/9/19.
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        Nominator zak = new Nominator("zak", 3);
        Nominator judd = new Nominator("judd", 1);
        Nominator bond = new Nominator("bond", 4);
        Nominator wason = new Nominator("wason", 2);
        Nominator sigmond = new Nominator("sigmond", 5);
//        TreeSet<Nominator> candidates = new TreeSet<>((o1, o2) -> o1.getVote() - o2.getVote());
        TreeSet<Nominator> candidates = new TreeSet<>();
        candidates.add(zak);
        candidates.add(judd);
        candidates.add(bond);
        candidates.add(wason);
        candidates.add(sigmond);
        System.out.println(candidates);
        NavigableSet<Nominator> descendingSet = candidates.descendingSet();
        System.out.println(descendingSet);
        List<Nominator> nominators = new ArrayList<>();
        nominators.add(zak);
        nominators.add(judd);
        nominators.add(bond);
        nominators.add(wason);
        nominators.add(sigmond);
//        Collections.sort(nominators, (o1, o2) -> o1.getVote() - o2.getVote());
        Collections.reverse(nominators);
        System.out.println(nominators);
        Nominator min = Collections.min(nominators);
        System.out.println(min);
        Nominator max = Collections.max(nominators);
        System.out.println(max);
//        Collections.sort(nominators);
        int[] intArray = {3, 1, 4, 2, 5};
        Arrays.sort(intArray);
        int search = Arrays.binarySearch(intArray, 2);
        System.out.println("search result is " + search);
        int index = Collections.binarySearch(nominators, zak);
        System.out.println("position is " + index);
        Collectors.toCollection(LinkedList::new);
    }
}
