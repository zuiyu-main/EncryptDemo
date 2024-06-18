package com.zuiyu.encryptdemo.encry.dao;

import com.github.yulichang.base.MPJBaseMapper;
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
public interface TestDmDao<T>  extends MPJBaseMapper<T> {
    /**
     * 达梦数据库联通测试
     * @return
     */
    Object getAll();

    List<Map<String,String>> likeQuery(String text);

    @Insert("INSERT INTO TABLE_1 (id,content)\n" +
            "VALUES (#{id},#{text});")
    void insert(String id,String text);
}
