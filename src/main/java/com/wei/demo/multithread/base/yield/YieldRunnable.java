package com.wei.demo.multithread.base.yield;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 *
 * 调用yeild，线程从“运行状态” -> “就绪状态”
 */
public class YieldRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            // i % 5 == 0时，当前线程让出cpu时间片，下一个执行的线程可能是线程1有可能是线程2或其他线程
            if (i % 5 == 0) {
                Thread.yield();
            }
        }
    }
}
