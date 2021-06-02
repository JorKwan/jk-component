package com.persagy.fm.mybatis.service;

/**
 * 数据库操作 Service
 * @author Charlie Yu
 * @date 2021-03-30
 */
public interface IDbService {

    /**
     * 创建数据库
     * @param schema 实例名
     */
    void createDataBase(String schema);
}
