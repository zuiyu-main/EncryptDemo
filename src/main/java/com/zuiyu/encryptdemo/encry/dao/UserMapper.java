package com.zuiyu.encryptdemo.encry.dao;

import com.github.yulichang.base.MPJBaseMapper;
import com.zuiyu.encryptdemo.encry.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author cxt
 */
@Mapper
@Repository
public interface UserMapper extends MPJBaseMapper<com.zuiyu.encryptdemo.encry.bean.User> {

}
