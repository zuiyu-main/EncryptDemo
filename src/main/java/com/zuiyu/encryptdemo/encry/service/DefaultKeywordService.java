package com.zuiyu.encryptdemo.encry.service;

import com.zuiyu.encryptdemo.encry.config.EncryptCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname DefaultKeywordService
 * @description TODO
 * @date 2024/4/16 14:37
 */
@Conditional(EncryptCondition.DbCondition.class)
@Service
public class DefaultKeywordService implements KeywordService{
    public final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public List<String> keyword(String text) {

        log.info("分词文本:[{}]",text);
        return loopEncryptString(text,2);
    }

    protected List<String> loopEncryptString(String input, int chunkSize) {
        int length = input.length();
        List<String> strList = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            StringBuilder chunkBuilder = new StringBuilder();
            for (int j = 0; j < chunkSize; j++) {
                int index = (i + j) % length;
                chunkBuilder.append(input.charAt(index));
            }
            strList.add(chunkBuilder.toString());

            log.info("第 {} 组:[{}]",i+1,chunkBuilder);
            // 如果到了最后一个分组，则不再循环第一个字符
            if (i + chunkSize >= length) {
                break;
            }
        }
        log.info("分词结果:[{}]",strList);
        return strList;
    }
}
