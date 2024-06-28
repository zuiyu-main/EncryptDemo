package com.zuiyu.encryptdemo.encry.test;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;


/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/6/25 13:51
 */
public class ThreadLocalTest implements Runnable{
    private static final ThreadLocal<String> MAIN_THREAD_LOCAL = ThreadLocal.withInitial(() -> "parent thread value: zuiyu java");

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("threadlocal 默认值："+MAIN_THREAD_LOCAL.get());
        MAIN_THREAD_LOCAL.set("child thread value :"+Thread.currentThread().getName());
        System.out.println("threadlocal 设置子线程值之后："+MAIN_THREAD_LOCAL.get());

    }

    public String get(){
        return MAIN_THREAD_LOCAL.get();
    }
    public void clean(){
        MAIN_THREAD_LOCAL.remove();
    }
    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        System.out.println("启动:"+threadLocalTest.get());
        for (int i = 0; i < 3; i++) {
//            new Thread(threadLocalTest).start();
            ThreadUtil.execAsync(threadLocalTest);

        }

        System.out.println("结束："+threadLocalTest.get());

    }
}
