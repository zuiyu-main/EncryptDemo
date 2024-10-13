package com.zuiyu.encryptdemo.encry.controller;

import cn.hutool.db.handler.HandleHelper;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test4")
public class Test4Controller {
    ThreadLocal<Map<String, Object>> threadLocal = new TransmittableThreadLocal(){
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }

        @Override
        public Object copy(Object parentValue) {
            return new HashMap<>((Map)parentValue);
        }
    };
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


    @RequestMapping("/ttl/{val}")
    public Object ttl(@PathVariable("val") Integer val) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(val + "", val);
        threadLocal.set(map);
        System.out.println("父线程set值=" + threadLocal.get());
        threadPoolExecutor.execute(TtlRunnable.get(() -> {
            System.out.println("线程池方式：子线程读取父线程的值：" + threadLocal.get());
            Map<String, Object> childMap = threadLocal.get();
            if (childMap == null) {
                childMap = new HashMap<>();
            }
            childMap.put("99", 99);
        }));
        try {
            Thread.sleep(2000);
        }catch (Exception e){}
        Map<String, Object> objectMap = threadLocal.get();
        System.out.println("父线程第二次读取threadlocal=" + objectMap);

        return "200";
    }
}
