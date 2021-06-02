package com.persagy.fm.mybatis.service.impl;

import com.persagy.fm.mybatis.dao.DbDao;
import com.persagy.fm.mybatis.service.IDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 数据库操作 Impl
 * @author Charlie Yu
 * @date 2021-03-30
 */
@Service
public class DbServiceImpl implements IDbService {

    @Resource
    private DbDao dao;

    @Override
    public void createDataBase(String schema) {
        dao.createDataBase(schema);
    }
}
