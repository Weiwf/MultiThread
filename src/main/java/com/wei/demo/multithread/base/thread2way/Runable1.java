package com.wei.demo.multithread.base.thread2way;

/**
 * @Author: weiwenfeng
 * @Date: 2018/11/29
 *
 * 实现Runnable:
 * (1)重写run()方法（任务），以把任务放到线程当中执行
 * (2)面向接口编程
 */
public class Runable1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Implements Runnable!");
    }
}
