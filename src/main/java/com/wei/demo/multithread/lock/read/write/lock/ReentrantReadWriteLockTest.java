package com.wei.demo.multithread.lock.read.write.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author weiwenfeng
 * @date 2018/12/6
 *
 * 读锁是“共享锁”，写锁是“排他锁”
 *
 * 获取读锁的前提：1.没有其他线程的写锁，2.没有其他线程的写请求，或，有写请求但调用线程和持有锁的线程是同一个
 * 获取写锁的前提：1.没有其他线程的写锁，2.没有其他线程的读锁
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        MyCount myCount = new MyCount(1000);
        User user = new User(myCount);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    user.getMoney();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    user.setMoney((int) (1000 * Math.random()));
                }
            }).start();
        }
    }
}

class User {
    private ReadWriteLock readWriteLock;

    private MyCount myCount;

    public User(MyCount myCount) {
        this.myCount = myCount;
        readWriteLock = new ReentrantReadWriteLock();
    }

    public void getMoney() {
        readWriteLock.readLock().lock();

        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName +" get money start ...");
            int count = myCount.getCount();
            Thread.sleep((long) (100 * Math.random()));
            System.out.println(threadName + " get " + count + " money!");
            System.out.println(threadName + " get money end ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public void setMoney(int count) {
        readWriteLock.writeLock().lock();

        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ------------ set Money start ----------");
            myCount.setCount(count);
            System.out.println(threadName + " set " + count + " money!");
            Thread.sleep(1000);
            System.out.println(threadName + " ------------ set money end ------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
}

class MyCount {
    private int count;

    public MyCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
