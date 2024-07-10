package com.zuiyu.encryptdemo.encry.controller;

import com.zuiyu.encryptdemo.encry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname TestController
 * @description TODO
 * @date 2024/4/16 10:14
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/put")
    public Object put(String text) throws Exception {
        testService.put(text);
        return "success";
    }
    @GetMapping("/get")
    public Object get(String text) throws Exception {
        return testService.get(text);
    }
    @GetMapping("/async")
    public Object async() throws Exception {
       testService.async();
        return "success";
    }
}
