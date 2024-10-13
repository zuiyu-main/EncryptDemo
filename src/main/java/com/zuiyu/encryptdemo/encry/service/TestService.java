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
    public void method1(Long tenantId){
        if (TenantContextHolder.getTenantId() == null){

        }
        TenantContextHolder.setTenantId(tenantId);
        log.info("主线程设置租户ID为：{}",tenantId);
        Runnable parentTask = (()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            log.info("子线程读取租户信息:[{}]",TenantContextHolder.getTenantId());
        });

       BizThreadPool.submit(parentTask);

       TenantContextHolder.clear();
       log.info("主线程读取租户值:[{}]",TenantContextHolder.getTenantId());
    }

    public void printThreadPoolStatus() {
        BizThreadPool.printThreadPoolStatus();
    }
}
