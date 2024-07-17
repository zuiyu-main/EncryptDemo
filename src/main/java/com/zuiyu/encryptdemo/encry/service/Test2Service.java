package com.zuiyu.encryptdemo.encry.service;

import com.zuiyu.encryptdemo.encry.bean.User;
import com.zuiyu.encryptdemo.encry.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/7/16 10:03
 */
@Slf4j
@Service
public class Test2Service {
    @Autowired
    private UserMapper userMapper;
//    @Transactional
    public void test2(){
        log.info("Test2Service 异步方法执行，执行线程:{}",Thread.currentThread().getName());
        User user = new User();
        user.setName("zuiyu Test2Service");
        user.setAge(18);
        user.setDeptId(1);
        userMapper.insert(user);
        int a = 22/0;
    }
}
