package com.wei.demo.multithread.atomic;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increase() {
        this.counter ++;
    }
}
