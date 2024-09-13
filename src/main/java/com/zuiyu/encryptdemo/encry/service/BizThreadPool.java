package com.zuiyu.encryptdemo.encry.service;

import java.util.concurrent.*;

/**
 * @author zuiyu
 * @date 2024/8/25
 * @description
 * @link <a href="https://github.com/zuiyu-main">zuiyu GitHub</a>
 */
public class BizThreadPool {

    public static final ExecutorService executor =
            new ThreadPoolExecutor(10,10,
                    60, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());
    public static final ExecutorService childThreadPool =
            new ThreadPoolExecutor(1,1,
                    60, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());
    public static Future<?> submit(Runnable runnable){
        return executor.submit(runnable);
    }
    public static Future<?> submitChildTask(Runnable runnable){
        return childThreadPool.submit(runnable);
    }
    public static void printThreadPoolStatus() {
        if (executor instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            System.out.println("Thread Pool Status:");
            System.out.println("Core Pool Size: " + threadPoolExecutor.getCorePoolSize());
            System.out.println("Maximum Pool Size: " + threadPoolExecutor.getMaximumPoolSize());
            System.out.println("Current Pool Size: " + threadPoolExecutor.getPoolSize());
            System.out.println("Active Count: " + threadPoolExecutor.getActiveCount());
            System.out.println("Completed Task Count: " + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("Task Count: " + threadPoolExecutor.getTaskCount());
            System.out.println("Queue Size: " + threadPoolExecutor.getQueue().size());
            System.out.println();
        } else {
            System.out.println("The provided ExecutorService is not an instance of ThreadPoolExecutor.");
        }
    }
}
