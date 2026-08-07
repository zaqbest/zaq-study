package com.zaqbest.study.atguigu_great_offer;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获得：" + lockB);
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获得：" + lockA);
            }
        }
    }
}
/**
 * 死锁是指两个及多个线程，
 * 因争抢资源而造成相互等待
 */

/**
 * Found one Java-level deadlock:
 * =============================
 * "ThreadBBB":
 *   waiting to lock monitor 0x00007fc83c020698 (object 0x000000076ad9ac58, a java.lang.String),
 *   which is held by "ThreadAAA"
 * "ThreadAAA":
 *   waiting to lock monitor 0x00007fc83c01e2d8 (object 0x000000076ad9ac90, a java.lang.String),
 *   which is held by "ThreadBBB"
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

    }
}
