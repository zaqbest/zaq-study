package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("*******come in Callable");
        //暂停一会线程
        try { TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e){e.printStackTrace();}
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FetureTask(Callable<V> callable)
        FutureTask<Integer> futureTask =
                new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        //！！！直接返回结果，不会重复计算
        new Thread(futureTask, "BB").start();


        //小tip
        while (!futureTask.isDone()){
            Thread.yield();
        }
        System.out.println("返回值:" + futureTask.get());
    }
}
