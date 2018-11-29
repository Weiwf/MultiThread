package com.wei.demo.multithread.base.example3;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 *
 * 线程调用sleep()方法进入“阻塞（休眠）状态”，重新被唤醒时，从“阻塞状态” -> “就绪状态”，等待获取cpu时间片
 */
public class SleepRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i % 3 == 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println("休眠1s...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
