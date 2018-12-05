package com.wei.demo.multithread.lock.cycli.barrier.example1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author weiwenfeng
 * @date 2018/12/5
 *
 * 所有线程相互等待，当指定数目的线程到达barrier后，再继续执行
 */
public class CycliBarrierTest {
    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Mythread(barrier, "线程" + i).start();
        }
    }
}

class Mythread extends Thread {
    private CyclicBarrier barrier;

    public Mythread(CyclicBarrier barrier, String name) {
        super(name);
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is watting at barrier!");
            //等待线程的数量+1
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " finished watting!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
