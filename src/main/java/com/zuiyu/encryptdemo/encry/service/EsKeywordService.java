package com.zuiyu.encryptdemo.encry.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuiyu.encryptdemo.encry.config.EncryptCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname EsKeywordService
 * @description TODO
 * @date 2024/4/16 15:28
 */
@Conditional(EncryptCondition.EsCondition.class)
@Service
public class EsKeywordService implements KeywordService{
    public final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${esUrl:192.168.160.112:9200}")
    private String esUrl;

    @Override
    public List<String> keyword(String text) {
        log.info("分词文本:[{}]",text);
        Map<String,String> jsonMap = new HashMap<>();
        jsonMap.put("text",text);
        jsonMap.put("analyzer","ik_max_word");

        String post = HttpUtil.post(esUrl+"/_analyze", JSON.toJSONString(jsonMap));

        JSONObject jsonObject = JSON.parseObject(post);
        JSONArray tokens = jsonObject.getJSONArray("tokens");
        List<String> res = new LinkedList<>();
        for (Object token : tokens) {
            JSONObject obj = JSON.parseObject(token.toString());
            res.add(obj.getString("token"));
        }
        log.info("分词结果:[{}]",res);
        return res;
    }
}
