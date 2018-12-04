package com.wei.demo.multithread.base.producer.and.comsumer.example1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: weiwenfeng
 * @Date: 2018/12/4
 * <p>
 * 利用阻塞队列实现生产者-消费者，不需要额外的同步，阻塞队中添加，移除元素相关的方法用ReentrantLock保证了并发操作
 */
public class ProducerConsumerUsingBlockingQueue {
    public static void main(String[] args) {
        ElementRepo elementRepo = new ElementRepo(10);
        Producer producer = new Producer(elementRepo, "生产者1");

        Thread consumer1 = new Consumer(elementRepo, "消费者1");
        Consumer consumer2 = new Consumer(elementRepo, "消费者2");
        Consumer consumer3 = new Consumer(elementRepo, "消费者3");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}

class ElementRepo {
    private BlockingQueue blockingQueue;

    public ElementRepo(int size) {
        blockingQueue = new ArrayBlockingQueue(size);
    }

    public void addElement() {
        try {
            blockingQueue.put(1);
            System.out.println("生产者线程 " + Thread.currentThread().getName() + " 生产了一个元素！" +
                    "当前队列大小：" + blockingQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeElement() {
        try {
            blockingQueue.take();
            System.out.println("消费者线程 " + Thread.currentThread().getName() + " 消费了一个元素！" +
                    "当前队列大小：" + blockingQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Producer extends Thread {
    private ElementRepo elementRepo;

    public Producer(ElementRepo elementRepo, String name) {
        super(name);
        this.elementRepo = elementRepo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                elementRepo.addElement();
                Thread.sleep((long) (300 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private ElementRepo elementRepo;

    public Consumer(ElementRepo elementRepo, String name) {
        super(name);
        this.elementRepo = elementRepo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                elementRepo.removeElement();
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


