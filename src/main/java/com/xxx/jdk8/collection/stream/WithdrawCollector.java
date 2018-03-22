package com.xxx.jdk8.collection.stream;

/**
 * Created by xiaowenzi on 2018/3/15.
 */
public class WithdrawCollector {
    private static WithdrawCollector instance = new WithdrawCollector();

    private WithdrawCollector() {}

    public static WithdrawCollector getInstance() {
        return instance;
    }

    public void fetchFromWs(String product) {
        if (product.equals("A06")) {
            throw new RuntimeException("连接三级分销接口出错");
        }
        System.out.println("从ws抓取"+product+"现金取款提案");
        doBussiness();
    }

    public void sendWaitingToKafka(String product) {
        System.out.println("将等待中的"+product+"现金取款提案发送到kafka");
        doBussiness();
    }

    public void sendRetryToKafka(String product) {
        System.out.println("将需要重审的"+product+"现金取款提案发送到kafka");
        doBussiness();
    }

    private void doBussiness() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
