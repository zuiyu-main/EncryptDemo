package com.zuiyu.encryptdemo.encry.controller;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.sun.scenario.effect.impl.ImagePool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test2")
public class Test2Controller {

    ThreadLocal<Map<String, Object>> transmittableThreadLocal = new TransmittableThreadLocal(){
        @Override
        public Object copy(Object parentValue) {
            return new HashMap<>((Map)parentValue);
        }

        @Override
        protected Object childValue(Object parentValue) {
            return super.childValue(parentValue);
        }
    };
    RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy(){
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("CallerRunsPolicy 策略，当前线程:[{}]"+Thread.currentThread().getName());
            super.rejectedExecution(r, e);
        }
    };
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(1),rejectedExecutionHandler);
    @RequestMapping("/set")
    public Object set()  {
        executor.execute(TtlRunnable.get(() -> {
            System.out.println("子线程"+Thread.currentThread().getName()+"进入睡眠3s");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        Map<String, Object> map = transmittableThreadLocal.get();
        if (null == map) {map = new HashMap<>();}
        map.put("mainThread", "主线程给的值:main");
        System.out.println("主线程赋值:" + map);
        transmittableThreadLocal.set(map);
        executor.execute(TtlRunnable.get(()->{
            System.out.println("第二次子线程"+Thread.currentThread().getName()+"输出："+transmittableThreadLocal.get());
        }));
        transmittableThreadLocal.remove();
        System.out.println("remove 结束");
        System.out.println("主线程remove之后读取:"+transmittableThreadLocal.get());

        return "";
    }
//    @RequestMapping("/set")
//    public Object set()  {
//
//        executor.execute(TtlRunnable.get(() -> {
//            Map<String, Object> map = transmittableThreadLocal.get();
//            if (null == map) {map = new HashMap<>();}
//            map.put("mainThread", "主线程给的值:main");
//            System.out.println("主线程赋值:" + map);
//            transmittableThreadLocal.set(map);
//            try{
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("子线程输出：" + Thread.currentThread().getName() + "读取父线程 TransmittableThreadLocal 的值:" + transmittableThreadLocal.get());
//            Map<String, Object> childMap = transmittableThreadLocal.get();
//            if (null == childMap){childMap = new HashMap<>();}
//            childMap.put("childThread","子线程添加值");
//        }));
////        Map<String, Object> stringObjectMap = transmittableThreadLocal.get();
////        if (null == stringObjectMap) {
////            stringObjectMap = new HashMap<>();
////        }
////        stringObjectMap.put("mainThread-2", "主线程第二次赋值");
////        transmittableThreadLocal.set(stringObjectMap);
////        try{
////            Thread.sleep(1000);
////        }catch (InterruptedException e){e.printStackTrace();}
////        System.out.println("主线程第二次输出ThreadLocal:"+transmittableThreadLocal.get());
//
//        executor.execute(TtlRunnable.get(()->{
//            System.out.println("第二次子线程"+Thread.currentThread().getName()+"输出："+transmittableThreadLocal.get());
//        }));
//        transmittableThreadLocal.remove();
//        System.out.println("remove 结束");
//        System.out.println("主线程remove之后读取:"+transmittableThreadLocal.get());
//
//        return "";
//    }
}

