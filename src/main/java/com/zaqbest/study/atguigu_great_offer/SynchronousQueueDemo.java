package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列演示
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new SynchronousQueue();

        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName() + "\t put A");
                blockingQueue.put("A");
                System.out.println(Thread.currentThread().getName() + "\t put B");
                blockingQueue.put("B");
                System.out.println(Thread.currentThread().getName() + "\t put C");
                blockingQueue.put("C");
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }, "AAA").start();


        new Thread(()->{
            try{
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take A");

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take B");

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t take C");
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }, "BBB").start();
    }
}
