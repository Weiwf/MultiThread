package com.wei.demo.multithread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class AtomicCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public AtomicInteger getCounter() {
        return counter;
    }

    public void setCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    public void increase(){
        this.counter.incrementAndGet();
    }
}
