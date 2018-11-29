package com.wei.demo.multithread.base.synchronize;

/**
 * @author weiwenfeng
 * @date 2018/11/29
 *
 * 同步的方法不影响其他线程对非同步方法的调用，但其线程调用另一个同步方法时，需要获得锁
 */
class Count{
    public synchronized void synMethod(){
        for (int i =0 ;i <5;i++){
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notSynMethod(){
        for (int i =0 ;i <5;i++){
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void anotheSynMethod(){
        for (int i =0 ;i <5;i++){
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SynAndNotSynTest {
    public static void main(String[] args) {
        Count count = new Count();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                count.synMethod();
            }
        },"线程1");
        th1.start();

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                count.notSynMethod();
            }
        },"线程2");
        th2.start();

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                count.anotheSynMethod();
            }
        },"线程3");
        th3.start();
    }
}
