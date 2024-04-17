package com.zuiyu.encryptdemo.encry.service;


import com.zuiyu.encryptdemo.encry.util.AESEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private KeywordService keywordService;

    @Autowired
    private IndexService indexService;

    public void put(String text) throws Exception {
        log.info("put,text:[{}]",text);
        List<String> chunks = keywordService.keyword(text);
        log.info("put,text chunks size :[{}]",chunks.size());
        StringBuilder str = new StringBuilder();
        for (String chunk : chunks) {
            String aes = AESEncryption.encrypt(chunk);
            str.append(aes);
        }
        log.info("index text :[{}]",str);
        indexService.index(str.toString());
    }
    public Object get(String text) throws Exception {
        log.info("get text :[{}]",text);
        List<String> chunks = keywordService.keyword(text);
        log.info("get,text chunks size :[{}]",chunks.size());
        // 公钥加密，私钥解密
        StringBuilder str = new StringBuilder();
        for (String chunk : chunks) {
            str.append(AESEncryption.encrypt(chunk));
        }
        log.info("get text :[{}]",str);
        Object search = indexService.search(str.toString());
        log.info("search result:[{}]",search);
        return search;
    }



}
