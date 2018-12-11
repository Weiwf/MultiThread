package com.wei.demo.multithread.lock.locksupport.example2;

import java.util.concurrent.locks.LockSupport;

/**
 * @author weiwenfeng
 * @date 2018/12/11
 * <p>
 * LockSupport支持先调用unpark()，再调用park()方法而不会阻塞线程
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //保证unpark()方法在park()方法调用之前
                    Thread.sleep(1000);
                    int sum = 0;
                    for (int i = 0; i < 1000; i++) {
                        sum += i;
                    }
                    LockSupport.park();

                    System.out.println(sum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

        LockSupport.unpark(thread);

    }
}
