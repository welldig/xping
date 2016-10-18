package com.welldig.xping.java;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xping.zong
 * Date: 2016/9/26
 * Time: 16:22
 * Description:
 */
public class Counter {
    static AtomicInteger count = new AtomicInteger(0);


    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {

        }
        plus();
    }

//    synchronized
    private static void plus() {
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override public void run() {
                    Counter.inc();
                }
            }).start();
        }
        while (count.get() != 1000);
        System.out.println(count);
    }
}
