package com.zaqbest.study.atguigu_great_offer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 配置参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 故障现象：
 *
 * 导致原因
 *
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）和缓冲区（Buffer)
 * 的IO方式。它可以使用Native函数直接分配堆外内存，然后通过一个存储在Java堆藜麦你的DirectByteBuffer对象
 * 作为这块内存的引用进行操作。
 * 这样在一些长泾镇显著提高心梗，因为避免了在java堆和native堆中来回复制数据。
 *
 * ByteBuffer.allocate(capability)第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度较慢
 *
 * ByteBuffer.allocateDirect(capability)，这种是分配OS本地内存，不属于GC管辖范围，不需要内存拷贝，所以会更快
 *
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC,DirectByteBuffer对象就不会回收
 * 这时候对内存充足，但本地内存可能已经用光了，再次尝试分配本地内存就会出现OutOfMemoryError,程序就直接崩溃了
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
//        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory()/(double)1024/1024) + "M");

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e) { e.printStackTrace();}

        //-XX:MaxDirectMemroySize=5m 我们配置为5m,但实际使用6m, 故意使坏
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);



    }
}
