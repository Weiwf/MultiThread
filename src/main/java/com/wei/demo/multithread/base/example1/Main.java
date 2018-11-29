package com.wei.demo.multithread.base.example1;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 */
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        thread1.start();

        Thread thread2 = new Thread(new Runable1());
        thread2.start();
    }
}
