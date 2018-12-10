package com.wei.demo.multithread.lock.condition.example2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weiwenfeng
 * @date 2018/12/10
 * <p>
 * 有界队列
 *
 * 可用多个Condition更加精细地控制等待/通知的线程：
 * 写入数据后，通知“读线程”；读出数据后，通知“写线程”；
 * 当队列满时，“写线程”需要等待；当队列为空时，“读线程”需要等待
 */
public class BoundedQueue {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Object[] elements;
    private int queueLength, addIndex, removeIndex;

    public BoundedQueue(int length) {
        elements = new Object[length];
    }

    public void addElement(Object object) throws InterruptedException {
        lock.lock();
        try {
            while (queueLength == elements.length) {
                notFull.await();
            }
            elements[addIndex] = object;
            if (++addIndex == elements.length) {
                addIndex = 0;
            }
            queueLength++;
            notEmpty.signal();
            System.out.println(Thread.currentThread().getName() + " add " + object);
            this.printQueue();
        } finally {
            lock.unlock();
        }
    }

    public Object removeElement() throws InterruptedException {
        lock.lock();
        try {
            while (0 == elements.length) {
                notEmpty.await();
            }
            Object object = elements[removeIndex];
            elements[removeIndex] = "已取出" + object;
            //将取出的位置为null，方便查看打印的队列元素
            if (++removeIndex == elements.length) {
                removeIndex = 0;
            }
            queueLength--;
            notFull.signal();

            System.out.println(Thread.currentThread().getName() + " remove " + object);
            this.printQueue();
            return object;
        } finally {
            lock.unlock();
        }
    }

    private void printQueue() {
        System.out.println("此时队列 " + Arrays.asList(this.elements) + "\r\n");
    }

}
