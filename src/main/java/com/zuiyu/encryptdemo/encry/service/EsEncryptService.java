package com.zuiyu.encryptdemo.encry.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zuiyu.encryptdemo.encry.config.EncryptCondition;
import com.zuiyu.encryptdemo.encry.config.EncryptConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname EsSearchService
 * @description TODO
 * @date 2024/4/16 16:03
 */
@Conditional(EncryptCondition.EsCondition.class)
@Service
public class EsEncryptService implements IndexService{
    public final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EncryptConfig config;

    @Override
    public void index(String encryptText) {
        log.info("ES index encryptText :[{}]",encryptText);
        Map<String,String> jsonMap = new HashMap<>();
        jsonMap.put("content",encryptText);
        log.info("调用URL:[{}]",config.getEsUrl()+"/"+config.getIndexName()+"/_doc");
        String post = HttpUtil.post(config.getEsUrl()+"/"+config.getIndexName()+"/_doc/"+ System.currentTimeMillis(), JSON.toJSONString(jsonMap));
        log.info("ES return :[{}]",post);
    }
    @Override
    public Object search(String encryptText){
        JSONObject p1 = new JSONObject();
        p1.put("content",encryptText);
        JSONObject p2 = new JSONObject();
        p2.put("match",p1);
        JSONObject p3 = new JSONObject();
        p3.put("query",p2);
        log.info("调用URL:[{}]",config.getEsUrl()+"/"+config.getIndexName()+"/_search");
        String post = HttpUtil.post(config.getEsUrl()+"/"+config.getIndexName()+"/_search", JSON.toJSONString(p3));
        JSONObject jsonObject = JSON.parseObject(post);
        JSONObject hits = jsonObject.getJSONObject("hits");
        return hits;
    }

}
