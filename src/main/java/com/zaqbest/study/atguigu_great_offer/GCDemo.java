package com.zaqbest.study.atguigu_great_offer;

import java.util.Random;

/**
 * 1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew+Tenered)
 *
 * 2,
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew+Tenered)
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 * 3
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC (PSYoungGen+ParOldGen)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 *
 * 4
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (ParNew+Concurrent mark-sweep generation)
 */
public class GCDemo {
    public static void main(String[] args) {

        System.out.println("**********GCDemo hello");

        try{
            String s = "atguigu";

            while (true){
                s += s + new Random().nextInt(777777777) + new Random().nextInt(888889990);
                s.intern();
            }
        } catch (Throwable e){
            e.printStackTrace();
        }

    }
}
