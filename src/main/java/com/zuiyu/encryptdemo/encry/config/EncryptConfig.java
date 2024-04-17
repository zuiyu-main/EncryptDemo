package com.zuiyu.encryptdemo.encry.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author create by zuiyu,github https://github.com/zuiyu-main
 * @classname EncryptConfig
 * @description TODO
 * @date 2024/4/17 10:53
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "encrypt")
public class EncryptConfig {

    public String type;
    public Integer dbChunk;
    public String esUrl;
    public String indexName;

}
