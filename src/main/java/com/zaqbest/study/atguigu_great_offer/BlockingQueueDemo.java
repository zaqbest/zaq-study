package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 1,队列
 * 2，阻塞队列
 *  2.1 阻塞队列有没有好的没有
 *  2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("A");
        blockingQueue.add("B");
        blockingQueue.add("C");
        blockingQueue.add("X");
    }
}
