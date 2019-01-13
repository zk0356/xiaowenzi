package com.xxx.jdk8.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaowenzi on 2018/3/15.
 */
public class WithdrawCollector {
    private static Logger logger = LoggerFactory.getLogger(WithdrawCollector.class);
    private static WithdrawCollector instance = new WithdrawCollector();

    private WithdrawCollector() {}

    public static WithdrawCollector getInstance() {
        return instance;
    }

    public void fetchFromWs(String product) {
//        if (product.equals("A06")) {
//            throw new RuntimeException("连接三级分销接口出错");
//        }
        logger.info("从ws抓取"+product+"现金取款提案");
        doBussiness();
    }

    public void sendWaitingToKafka(String product) {
        logger.info("将等待中的"+product+"现金取款提案发送到kafka");
        doBussiness();
    }

    public void sendRetryToKafka(String product) {
        logger.info("将需要重审的"+product+"现金取款提案发送到kafka");
        doBussiness();
    }

    private void doBussiness() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
