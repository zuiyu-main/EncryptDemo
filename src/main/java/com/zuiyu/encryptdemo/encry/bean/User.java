package com.zuiyu.encryptdemo.encry.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/6/17 17:22
 */
@Data
@TableName(value = "users",autoResultMap = true)
public class User {
    private Integer id;

    private String name;

    @TableField(value = "age")
    private Integer age;
    @TableField(value = "dept_id")
    private Integer deptId;
}
