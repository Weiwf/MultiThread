package com.wei.demo.multithread.base.yield;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 *
 * yield不会释放锁，每个线都会讲任务执行完（打印0-9数字）才轮到其他线程执行
 */
public class YieldDoNotReleaseLockRunnable implements Runnable {
    @Override
    public synchronized void run() {
        System.out.println("-----------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i % 5 == 0) {
                Thread.yield();
            }
        }
    }
}
