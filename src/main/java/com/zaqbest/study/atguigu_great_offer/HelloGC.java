package com.zaqbest.study.atguigu_great_offer;

/**
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=16m
 *
 * 查看垃圾收集器
 */
public class HelloGC {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机的内存总量
        long maxMemroy = Runtime.getRuntime().maxMemory(); //返回java虚拟机试图使用的最大内存量

        System.out.println("Total Memory(-Xms)" + totalMemory + "(字节)," + (totalMemory/(double)1024/1024) + "MB");
        System.out.println("Max Memory(-Xmx)" + maxMemroy + "(字节)," + (maxMemroy/(double)1024/1024) + "MB");
    }
}
