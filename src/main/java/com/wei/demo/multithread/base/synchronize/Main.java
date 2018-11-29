package com.wei.demo.multithread.base.synchronize;

/**
 * @author weiwenfeng
 * @date 2018/11/29
 */
public class Main {
    public static void main(String[] args) {
        Runnable runnable = new SynchronizedThread();
        Thread th1 = new Thread(runnable,"线程1");
        Thread th2 = new Thread(runnable,"线程2");
        th1.start();
        th2.start();
    }
}
