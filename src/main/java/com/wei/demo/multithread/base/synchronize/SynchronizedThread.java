package com.wei.demo.multithread.base.synchronize;

/**
 * @author weiwenfeng
 * @date 2018/11/29
 *
 *加锁后一个线程执行完再轮到另一个线程
 */
public class SynchronizedThread implements Runnable{

    @Override
    public synchronized void run() {
        for (int i = 0;i < 10;i++){
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
