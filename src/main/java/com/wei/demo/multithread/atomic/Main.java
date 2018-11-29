package com.wei.demo.multithread.atomic;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class Main {
    public static void main(String[] args) {
        notAtomicCounterTest();
        atomicCounterTest();
    }

    /**
     * 非原子计数器的测试方法
     */
    public static void notAtomicCounterTest() {
        Counter counter = new Counter();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        counter.increase();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(counter.getCounter());
    }

    /**
     * 原子计数的测试方法
     */
    public static void atomicCounterTest() {
        AtomicCounter atomicCounter = new AtomicCounter();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        atomicCounter.increase();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(atomicCounter.getCounter());
    }
}
