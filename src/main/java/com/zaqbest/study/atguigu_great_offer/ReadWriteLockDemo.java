package com.zaqbest.study.atguigu_great_offer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
class MyCache{
    //缓冲使用volatile保证可见性
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        lock.writeLock().lock();
        try{
        System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
        //暂停一会线程
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + value);
        } finally {
            lock.readLock().unlock();
        }
    }
}
/**
 * 读写锁示例
 * @create 2020-10-15 19:12
 *
 * 多个线程同时读一个资源灭有问题，所以为了满足并发量，读取共享资源可以同时进行。
 * 但是
 * 如果有一个线程写资源，就不能有其他线程可以进行资源的读和写
 * 小总结：
 *     读-读：可以共存
 *     其他：不能共存
 *
 *     写操作：原子+独占，整个过程必须是一个完整的统一体，中间不允许被分割和打断。
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++){
            int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++){
            int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
