package com.wei.demo.multithread.lock.locksupport.example1;

import java.util.concurrent.locks.LockSupport;

/**
 * @author weiwenfeng
 * @date 2018/12/11
 * <p>
 * LockSupport可通过park()/unpark()方法实现对线程的阻塞/解除阻塞
 */
public class LockSuportTest {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getName() + " start ...");

        Thread myThread = new MyThread(mainThread, "子线程");
        myThread.start();

        System.out.println(mainThread.getName() + " is parking!");

        LockSupport.park(mainThread);

        System.out.println(mainThread.getName() + " continue ...");
    }
}

class MyThread extends Thread {
    private Thread unparkThread;

    public MyThread(Thread unparkThread, String name) {
        super(name);
        this.unparkThread = unparkThread;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " unpark " + unparkThread.getName());
            LockSupport.unpark(unparkThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
