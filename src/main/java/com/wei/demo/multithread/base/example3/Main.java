package com.wei.demo.multithread.base.example3;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class Main {
    public static void main(String[] args) {
        sleepDoNotReleaseLockTest();
    }

    public static void sleepTest(){
        Runnable runnable = new SleepRunnable();
        Thread thread = new Thread(runnable,"线程1");
        thread.start();
    }

    public static void sleepDoNotReleaseLockTest(){
        Runnable runnable = new SleepDoNotReleaseLockRunnable();
        Thread thread1 = new Thread(runnable,"线程1");
        Thread thread2 = new Thread(runnable,"线程2");
        Thread thread3 = new Thread(runnable,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
