package com.xxx.jdk8.stream;

import com.xxx.jdk8.MathFunctions;
import com.xxx.jdk8.Nominator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(StreamDemo.class);

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
        logger.info(time2.compose(square).apply(3).toString());
        logger.info(time2.andThen(square).apply(3).toString());
        DoubleSummaryStatistics doubleSummaryStatistics = Stream.of(3.2, 4.56, 2.01, 67.1)
                                                                .mapToDouble(Double::doubleValue)
                                                                .summaryStatistics();
        logger.info(String.valueOf(doubleSummaryStatistics.getSum()));
        logger.info(String.valueOf(doubleSummaryStatistics.getMax()));

        Double reduce = Stream.of(4.53, 27.1, 6.09, 87.51, 10.2)
                              .reduce(0.0, MathFunctions.SUM);
        logger.info(reduce.toString());

//        StreamSupport.stream();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        random.ints()
              .filter(value -> value > 0)
              .limit(20)
              .peek(value -> logger.info(value+","))
              .toArray();
    }

    public void streamApi(List<Nominator> nominatorList) {
        nominatorList.stream()
                     .peek(nominator -> logger.info("before distinct:" + nominator))
                     .distinct()
                     .forEach(nominator -> logger.info("after distinct:" + nominator));
    }


}
