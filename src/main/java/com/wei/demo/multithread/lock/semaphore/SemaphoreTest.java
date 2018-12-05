package com.wei.demo.multithread.lock.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author weiwenfeng
 * @date 2018/12/5
 *
 *
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int semNum = 10;
        Semaphore semaphore = new Semaphore(semNum);
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new MyThread(semaphore, 6));
        service.execute(new MyThread(semaphore, 3));
        service.execute(new MyThread(semaphore, 8));
    }
}

class MyThread extends Thread {
    private Semaphore semaphore;
    private int count;

    public MyThread(Semaphore semaphore, int count) {
        this.semaphore = semaphore;
        this.count = count;
    }

    public void run() {
        try {
            //获取指定数目的信号量许可，当信号量中有可用的许可时，线程能获取该许可；否则线程必须等待，直到有可用的许可为止
            semaphore.acquire(count);
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " acquire count=" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放许可
            semaphore.release(count);
            System.out.println(Thread.currentThread().getName() + " release " + count + " count.");
        }

    }
}
