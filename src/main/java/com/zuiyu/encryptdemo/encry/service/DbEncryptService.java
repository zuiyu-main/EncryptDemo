package com.zuiyu.encryptdemo.encry.service;

import com.zuiyu.encryptdemo.encry.config.EncryptCondition;
import com.zuiyu.encryptdemo.encry.dao.TestDmDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname DefaultService
 * @description TODO
 * @date 2024/4/17 10:09
 */
@Conditional(EncryptCondition.DbCondition.class)
@Service
public class DbEncryptService implements IndexService{

    public final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestDmDao testDmDao;

    @Override
    public void index(String encryptText) {
        log.info("DB insert encryptText :[{}]",encryptText);
        testDmDao.insert(UUID.randomUUID().toString(),encryptText);
    }

    @Override
    public Object search(String encryptText) {
        Object all = testDmDao.likeQuery(encryptText);
        if (null == all){
            log.warn("查询数据为空");
            return all;
        }
        return all;
    }
}
