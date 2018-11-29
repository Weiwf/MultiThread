package com.wei.demo.multithread.base.example3;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class SleepDoNotReleaseLockRunnable implements Runnable {
    @Override
    public synchronized void run() {
        System.out.println("---------------");
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
