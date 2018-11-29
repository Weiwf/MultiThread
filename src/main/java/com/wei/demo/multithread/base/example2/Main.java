package com.wei.demo.multithread.base.example2;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class Main {
    public static void main(String[] args) {
        yieldDoNotReleaseLockTest();
    }

    public static void yieldTest(){
        Runnable runnable = new YieldRunnable();
        Thread thread1 = new Thread(runnable,"线程1");
        Thread thread2 = new Thread(runnable,"线程2");
        Thread thread3 = new Thread(runnable,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void yieldDoNotReleaseLockTest(){
        Runnable runnable = new YieldDoNotReleaseLockRunnable();
        Thread thread1 = new Thread(runnable,"线程1");
        Thread thread2 = new Thread(runnable,"线程2");
        Thread thread3 = new Thread(runnable,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
