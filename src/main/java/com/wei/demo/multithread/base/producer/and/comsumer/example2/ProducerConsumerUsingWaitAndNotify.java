package com.wei.demo.multithread.base.producer.and.comsumer.example2;

/**
 * @Author: weiwenfeng
 * @Date: 2018/12/4
 * <p>
 * 利用synchronized,wait(),notifyAll()实现生产者，消费者
 */
public class ProducerConsumerUsingWaitAndNotify {
    public static void main(String[] args) {
        ElementRepo elementRepo = new ElementRepo(10, 0);
        Producer producer = new Producer(elementRepo, "生产者1");

        Consumer consumer1 = new Consumer(elementRepo, "消费者1");
        Consumer consumer2 = new Consumer(elementRepo, "消费者2");
        Consumer consumer3 = new Consumer(elementRepo, "消费者3");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}

/**
 * 当添加元素时大于等于最大容量，或移除元素时小于等于最小容量时 -> 通知线程进入等待状态；
 * 当成功添加元素，或移除元素时 -> 通知线程来消费或生产元素
 */
class ElementRepo {
    private int currentSize = 0;

    private int maxSize;
    private int minSize;

    public ElementRepo(int maxSize, int minSize) {
        this.maxSize = maxSize;
        this.minSize = minSize;
    }

    public synchronized void addElement() {
        if (currentSize >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            currentSize++;
            notifyAll();
            System.out.println("线程：" + Thread.currentThread().getName() + " 生产了一个元素！" +
                    "当前元素大小：" + currentSize);
        }
    }

    public synchronized void removeElement() {
        if (currentSize <= minSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            currentSize--;
            notifyAll();
            System.out.println("线程：" + Thread.currentThread().getName() + " 消费了一个元素！" +
                    "当前元素大小：" + currentSize);
        }
    }
}

class Producer extends Thread {
    private ElementRepo elementRepo;

    public Producer(ElementRepo elementRepo, String name) {
        super(name);
        this.elementRepo = elementRepo;
    }

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


