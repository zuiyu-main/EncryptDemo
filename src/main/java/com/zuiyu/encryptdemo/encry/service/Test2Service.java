package com.zuiyu.encryptdemo.encry.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname TestService
 * @description TODO
 * @date 2024/4/16 10:16
 */
@Service
public class Test2Service {
    public final Logger log = LoggerFactory.getLogger(getClass());
    public void childTask(CountDownLatch latch){
        Runnable childTask = (()->{
            log.info("子任务，childTask");
            latch.countDown();
        });
        BizThreadPool.submitChildTask(childTask);
    }

}
