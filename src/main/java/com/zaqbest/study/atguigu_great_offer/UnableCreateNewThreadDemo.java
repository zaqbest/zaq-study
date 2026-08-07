package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.TimeUnit;

/**
 * 高并发请求服务器时，经常出现如下异常：java.lang.OutOfMemoryError:unable to create new native thread
 * 准确的讲该native thread异常与对应的平台有关
 *
 * 导致原因：
 * 1，你的应用创建了太多线程了，一个应用进程创建多个线程，超过系统承载值
 * 2，你的服务器并不允许你的应用创建这么多线程，linux系统默认允许单个进程创建1024个线程
 * 你的应用创建超过这个数量，就会包java.lang.OutOfMemoryError:unable to create new native thread
 *
 * 解决办法
 * 1，想办法减低你应程序创建线程的数量，分析是否真的需要创建这么多线程，如果不是就降低线程数
 * 2，对于有的应用，确实需要创建很多线程，远超过linux系统默认1024个，可以修改linux配置
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ;i++){
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.println("*******"+ finalI);
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
