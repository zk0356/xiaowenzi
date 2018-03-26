package com.xxx.jdk8.collection.stream;

import com.xxx.jdk8.collection.MathFunctions;
import com.xxx.jdk8.collection.Nominator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Stream;

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

        StreamDemo demo = new StreamDemo();
        demo.streamApi(nominatorList);
        Function<Integer, Integer> time2 = integer -> integer * 2;
        Function<Integer, Integer> square = (Function<Integer, Integer> & Serializable) integer -> integer * integer;
        System.out.println(time2.compose(square).apply(3));
        System.out.println(time2.andThen(square).apply(3));
        DoubleSummaryStatistics doubleSummaryStatistics = Stream.of(3.2, 4.56, 2.01, 67.1)
                                                                .mapToDouble(Double::doubleValue)
                                                                .summaryStatistics();
        System.out.println(doubleSummaryStatistics.getSum());
        System.out.println(doubleSummaryStatistics.getMax());

        Double reduce = Stream.of(4.53, 27.1, 6.09, 87.51, 10.2)
                              .reduce(0.0, MathFunctions.SUM);
        System.out.println(reduce);

//        StreamSupport.stream();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        random.ints()
              .filter(value -> value > 0)
              .limit(20)
              .peek(value -> System.out.println(value+","))
              .toArray();
    }

    public void streamApi(List<Nominator> nominatorList) {
        nominatorList.stream()
                     .peek(nominator -> System.out.println("before distinct:" + nominator))
                     .distinct()
                     .forEach(nominator -> System.out.println("after distinct:" + nominator));
    }


}
