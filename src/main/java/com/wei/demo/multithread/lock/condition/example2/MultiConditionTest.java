package com.wei.demo.multithread.lock.condition.example2;

/**
 * @author weiwenfeng
 * @date 2018/12/10
 */
public class MultiConditionTest {
    public static void main(String[] args) {
        BoundedQueue boundedQueue = new BoundedQueue(5);
        for (int i = 0; i < 10; i++) {
            new AddThread(boundedQueue, "Add-Thread" + i, i ).start();
            new RemoveThread(boundedQueue,"Remove-Thread" + i).start();
        }
    }
}

class AddThread extends Thread {
    private BoundedQueue boundedQueue;
    private int element;

    public AddThread(BoundedQueue boundedQueue, String name, int element) {
        super(name);
        this.boundedQueue = boundedQueue;
        this.element = element;
    }

    public void run() {
        try {
            boundedQueue.addElement(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RemoveThread extends Thread {
    private BoundedQueue boundedQueue;

    public RemoveThread(BoundedQueue boundedQueue, String name) {
        super(name);
        this.boundedQueue = boundedQueue;
    }

    public void run() {
        try {
            boundedQueue.removeElement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
