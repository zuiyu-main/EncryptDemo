package com.zuiyu.encryptdemo.encry.service;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname TestService
 * @description TODO
 * @date 2024/4/16 10:16
 */
@Service
public class TestService {
    public final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private Test2Service test2Service;
    public void method1(){
        Runnable parentTask = (()->{
//            log.info("父任务，parentTask");
//            CountDownLatch latch = new CountDownLatch(3);
//            for (int i = 0; i < 3; i++) {
//                test2Service.childTask(latch);
//            }
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            log.info("父任务，parentTask 完成");
            System.out.println(TenantContextHolder.getTenantId());

//            TenantContextHolder.clear();
        });
       BizThreadPool.submit(parentTask);

    }

    public void printThreadPoolStatus() {
        BizThreadPool.printThreadPoolStatus();
    }
}
