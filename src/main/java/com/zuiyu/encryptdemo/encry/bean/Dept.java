package com.zuiyu.encryptdemo.encry.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/6/17 17:23
 */
@Data
@TableName(value = "dept",autoResultMap = true)
public class Dept {
    private Integer id;

    private String name;
}
