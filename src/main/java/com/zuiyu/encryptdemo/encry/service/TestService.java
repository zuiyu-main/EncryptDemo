package com.zuiyu.encryptdemo.encry.service;


import com.zuiyu.encryptdemo.encry.bean.User;
import com.zuiyu.encryptdemo.encry.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
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
    private UserMapper userMapper;

    @Autowired
    private Test2Service test2Service;
    @Transactional
    public void test(){
        log.info("TestService 方法执行，执行线程:{}",Thread.currentThread().getName());
        User user = new User();
        user.setName("zuiyu");
        user.setAge(18);
        user.setDeptId(1);
        userMapper.insert(user);
        test2Service.test2();

        int a = 22/0;
    }

//    @Bean("zuiyuThreadPool")
//    public Executor zuiyuThreadPool(){
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(8);
//        threadPoolTaskExecutor.setMaxPoolSize(16);
//        threadPoolTaskExecutor.setQueueCapacity(100);
//        threadPoolTaskExecutor.setKeepAliveSeconds(60);
//        threadPoolTaskExecutor.setThreadNamePrefix("zuiyuThreadPool-");
//        threadPoolTaskExecutor.initialize();
//        return threadPoolTaskExecutor;
//    }

//    @Async("zuiyuThreadPool")
//    public CompletableFuture<String> async(){
//        log.info("异步线程消息输出:{}",Thread.currentThread().getName());
//        return CompletableFuture.completedFuture("zuiyu-java");
//    }
//    @Async("zuiyuThreadPool")
    @Async
    public void async(){
        log.info("异步线程消息输出:{}",Thread.currentThread().getName());
    }
}
