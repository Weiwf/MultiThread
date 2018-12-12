package com.wei.demo.multithread.threadpool.callable.and.futrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author weiwenfeng
 * @date 2018/12/12
 */
public class CallableAndFutrueTest {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Callable callable = new MyCallable();

        System.out.println("提交任务到线程池!");

        Future future = pool.submit(callable);

        System.out.println("任务执行结果：" + future.get());

        pool.shutdown();
    }
}
