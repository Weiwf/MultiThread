package com.wei.demo.multithread.base.join;

/**
 * @author weiwenfeng
 * @date 2018/11/30
 *
 * 在main线程中起了一个子线程后，调用子线程的join()方法，那么main线程会进入阻塞状态
 * 只有当子线程运行结束时，main线程才会结束
 *
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程结束！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"子线程");
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程结束！");
    }
}
