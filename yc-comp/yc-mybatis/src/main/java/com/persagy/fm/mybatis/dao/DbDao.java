package com.persagy.fm.mybatis.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 数据库操作dao
 * @author Charlie Yu
 * @date 2021-03-30
 */
public interface DbDao {

    /**
     * 创建schema
     * @param schema 实例名
     */
    void createDataBase(@Param("schema") String schema);
}
