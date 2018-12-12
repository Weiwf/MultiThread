package com.wei.demo.multithread.threadpool.callable.and.futrue;

import java.util.concurrent.Callable;

/**
 * @author weiwenfeng
 * @date 2018/12/12
 */
public class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        try {
            System.out.println("任务执行中 ...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Call result";
    }
}
