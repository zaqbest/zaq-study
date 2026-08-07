package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch演示
 *
 * 秦灭六国，一统天下
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int N = 6;
        CountDownLatch countDownLatch = new CountDownLatch(N);

        for (int i = 0; i < N; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 锁门");
    }
}
