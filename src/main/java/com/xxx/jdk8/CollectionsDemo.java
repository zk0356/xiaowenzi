package com.xxx.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by xiaowenzi on 2017/9/19.
 */
public class CollectionsDemo {
    private static Logger logger = LoggerFactory.getLogger(CollectionsDemo.class);

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
        logger.info("treeset:" + candidates);
        NavigableSet<Nominator> descendingSet = candidates.descendingSet();
        logger.info("dectreeset:" + descendingSet);
        List<Nominator> nominators = new ArrayList<>();
        nominators.add(zak);
        nominators.add(judd);
        nominators.add(bond);
        nominators.add(wason);
        nominators.add(sigmond);
//        Collections.sort(nominators, (o1, o2) -> o1.getVote() - o2.getVote());
        Collections.sort(nominators);
        logger.info("sort:" + nominators);
        Collections.reverse(nominators);
        logger.info("reverse:" + nominators);
        Nominator min = Collections.min(nominators);
        logger.info("min:" + min);
        Nominator max = Collections.max(nominators);
        logger.info("max:" + max);

        int[] intArray = {3, 1, 4, 2, 5};
        Arrays.sort(intArray);
        int position = Arrays.binarySearch(intArray, 4);
        logger.info("position is " + position);
        int index = Collections.binarySearch(nominators, bond);
        logger.info("index is " + index);
    }
}
