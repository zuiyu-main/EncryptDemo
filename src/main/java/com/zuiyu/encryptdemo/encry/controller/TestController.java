package com.zuiyu.encryptdemo.encry.controller;

import com.zuiyu.encryptdemo.encry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @GetMapping("/test")
    public Object test() throws Exception {
        testService.test();
        return "success";
    }
}
