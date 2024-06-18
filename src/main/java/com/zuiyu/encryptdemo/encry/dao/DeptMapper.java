package com.zuiyu.encryptdemo.encry.dao;

import com.github.yulichang.base.MPJBaseMapper;
import com.zuiyu.encryptdemo.encry.bean.Dept;
import com.zuiyu.encryptdemo.encry.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cxt
 */
@Mapper
@Repository
public interface DeptMapper extends MPJBaseMapper<Dept> {

}
