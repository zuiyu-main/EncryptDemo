package com.zuiyu.encryptdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EncryptDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptDemoApplication.class, args);
    }

}
