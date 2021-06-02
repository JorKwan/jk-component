package com.persagy.fm.mybatis.dao;

import org.apache.ibatis.annotations.Param;


/**
 * 树编码使用的dao
 * @author Charlie Yu
 * @date 2021-03-29
 */
public interface SimpleTreeCodeDao {

    /**
     * 取得指定表指定树层次的最大编码
     * @param tableName      表名
     * @param parentCode     父节点编码
     * @param innerCodeField 内码字段名
     * @return 最大编码
     */
    String getMaxCode(@Param("tableName") String tableName, @Param("parentCode") String parentCode, @Param("innerCodeField") String innerCodeField);

    /**
     * 更新指定表的指定上级的实体的树编码
     * @param tableName     表名
     * @param parentCode    新的上级编码
     * @param oldParentCode 旧的上级编码
     * @param innerCodeField 内码字段名
     */
    void updateSubTreeCode(@Param("tableName") String tableName, @Param("parentCode") String parentCode, @Param("oldParentCode") String oldParentCode, @Param("innerCodeField") String innerCodeField);
}
