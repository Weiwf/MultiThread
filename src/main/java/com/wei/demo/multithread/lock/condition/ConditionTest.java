package com.wei.demo.multithread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weiwenfeng
 * @date 2018/12/9
 *
 * Object：synchronized + wait()/noitify()/noitifyAll()
 * Condiftion: Lock + await()/signal()/signalAll()
 */
public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            System.out.println("main start ...");
            MyThread myThread = new MyThread(lock, condition, "线程1");
            myThread.start();
            System.out.println(Thread.currentThread().getName() + " is watting ...");
            // main线程进入等待状态
            condition.await();
            System.out.println(Thread.currentThread().getName() + " continue!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyThread extends Thread {
    private Lock lock;
    private Condition condition;

    public MyThread(Lock lock, Condition condition, String name) {
        super(name);
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " call notifyAll() ...");
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
