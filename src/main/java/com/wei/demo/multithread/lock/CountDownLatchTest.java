package com.wei.demo.multithread.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: weiwenfeng
 * @Date: 2018/12/5
 * 在main线程设置了CountDownLatch的计数器，然后在main线程中调用CountDownLatch的await()方法，
 * main线程需要等到count的计数变为0才能继续执行
 *
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        System.out.println("------- main 线程开始 ---------");
        int countNum = 5;
        CountDownLatch latch = new CountDownLatch(countNum);
        for (int i = 0; i < countNum; i++) {
            new MyThread(latch, "线程" + i).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------- main 线程结束 ---------");
    }
}

class MyThread extends Thread {
    private CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch, String name) {
        super(name);
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " finished!");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
