package com.zuiyu.encryptdemo.encry.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test3")
public class Test3Controller {
    static ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

    static {
        blockingQueue.add(1);
        blockingQueue.add(2);
    }
    @RequestMapping("/set/{params}")
    public Object set(@PathVariable("params") Integer params) {
//        blockingQueue.add(params);
        blockingQueue.offer(params);
        return "200";
    }
    @RequestMapping("/poll")
    public Object poll() {
        Integer element = blockingQueue.poll();
        if (element != null) {
            System.out.println("Polled element: " + element);
            return element;
        } else {
            System.out.println("Queue was empty");
            return null;
        }
    }
    @RequestMapping("/pollTime")
    public Object pollTime() throws InterruptedException {
        Integer element = blockingQueue.poll(5, TimeUnit.SECONDS);
        if (element != null) {
            System.out.println("Polled element: " + element);
            return element;
        } else {
            System.out.println("Queue was empty");
            return null;
        }
    }
    @RequestMapping("/take")
    public Object take() {
        try {
            Integer element = blockingQueue.take();
            System.out.println("Taken element: " + element);
            return element;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
