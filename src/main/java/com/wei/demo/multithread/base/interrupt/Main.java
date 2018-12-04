package com.wei.demo.multithread.base.interrupt;

/**
 * @author weiwenfeng
 * @date 2018/12/4
 * <p>
 * (1)对“阻塞状态”的线程调用interrupt() -> 设置中断标记为true，然后因为处于阻塞状态，中断标记被清除(设置为false)，
 * 并抛出InterruptedException
 * (2)对“运行状态”线程调用interrupt() - > 设置中断标记为true
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Thread th = new MyThread1("线程1");
        System.out.println(th.getName() + ",state:" + th.getState() + " ,is new ");
        th.start();
        Thread.sleep(100);
        th.interrupt();
        System.out.println(th.getName() + ",state:" + th.getState() + " ,is terminated");
    }
}

/**
 * 异常在while循环外捕获可以终止循环
 */
class MyThread1 extends Thread {

    public MyThread1(String threadName) {
        super(threadName);
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + ",state:" + this.getState());
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ",state:" + this.getState() +
                    ",catch Exception:" + e);
        }

    }
}

/**
 * 异常在while循环内捕获不可以终止循环
 */
class MyThread2 extends Thread {

    public MyThread2(String threadName) {
        super(threadName);
    }

    public void run() {
        while (!isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + ",state:" + this.getState());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ",state:" + this.getState() +
                        ",catch Exception:" + e);
            }
        }
    }
}
