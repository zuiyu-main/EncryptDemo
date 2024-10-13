package com.zuiyu.encryptdemo.encry.controller;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.zuiyu.encryptdemo.encry.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname TestController
 * @description TODO
 * @date 2024/4/16 10:14
 */
@Slf4j
@RestController
@RequestMapping("/test1")
public class Test1Controller {
    @Autowired
    private TestService testService;
    static final ExecutorService executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    static final ThreadLocal<Map<Long,Long>> threadlocal = new TransmittableThreadLocal<Map<Long,Long>>(){
        @Override
        protected Map<Long, Long> childValue(Map<Long, Long> parentValue) {
            return new LinkedHashMap<>(parentValue);
        }

        @Override
        protected Map<Long, Long> initialValue() {
            log.info("threadlocal initialValue");
            return new LinkedHashMap<>();
        }

//        @Override
//        public Map<Long, Long> copy(Map<Long, Long> parentValue) {
//            return new LinkedHashMap<>(parentValue);
//        }
    };

//    static {
//        init();
//    }
    /**
     * 第一种情况，
     * @param tenantId
     * @return
     */
    @GetMapping("/ttl")
    public Object put(Long tenantId){
        Map<Long, Long> longLongMap = threadlocal.get();
        if (longLongMap == null){
            init();
        }
        log.info("主线程读取:[{}]",longLongMap);
        log.info("主线程设置tenantId:[{}]",tenantId);
        threadlocal.get().put(tenantId,tenantId);
        executor.execute(TtlRunnable.get(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            log.info("子线程读取:[{}]",threadlocal.get());
        }));
        threadlocal.get().put(tenantId+1,tenantId+1);
        threadlocal.remove();
        log.info("主线程结束");
        return "success";
    }



    @GetMapping("/info")
    public Object get() throws Exception {
        testService.printThreadPoolStatus();
        return "success";
    }

    public static synchronized void init(){
        Map<Long, Long> map = threadlocal.get();
        if (map == null){
            map = new LinkedHashMap<>();
        }
        log.info("threadlocal init");
        threadlocal.set(map);
    }
}
