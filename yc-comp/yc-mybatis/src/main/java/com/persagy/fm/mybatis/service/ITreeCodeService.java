package com.persagy.fm.mybatis.service;

import com.persagy.yc.common.model.entity.ITreeEntity;

import java.util.List;

/**
 * 树代码生成引擎
 * @author Charlie Yu
 * @date 2021-03-29
 */
public interface ITreeCodeService {

    /** 树编码每级码长 */
    int TREE_CODE_LENGTH = 5;

    /**
     * 为指定实体生成树编码
     * @param entity     树形实体
     * @param parentCode 上级编码
     * @return 树形实体
     */
    <T extends ITreeEntity> T generateTreeCode(T entity, String parentCode);

    /**
     * 根据上级实体生成下级编码，如果上级实体为空则传入空实体
     * @param parent 上级实体
     * @return 树形编码
     */
    <T extends ITreeEntity> String generateTreeCodeByParent(T parent);

    /**
     * 根据表名上级节点id上级节点编码生成新的下级编码
     * @param tableName      表名
     * @param parentCode     上级节点编码
     * @param innerCodeField
     * @return 新的编码
     */
    String generateTreeCode(String tableName, String parentCode, String innerCodeField);

    /**
     * 为一组相同上级的实体生成树编码
     * @param entitys    树形实体数组
     * @param parentCode 櫖上级编码
     * @return 树形实体数组
     */
    <T extends ITreeEntity> List<T> generateTreeCodes(List<T> entitys, String parentCode);

    /**
     * 根据上级实体生成多个下级编码，如果上级实体为空则传入空实体
     * @param parent 上级实体
     * @param length 编码个数
     * @return 编码数组
     */
    <T extends ITreeEntity> String[] generateTreeCodesByParent(T parent, int length);

    /**
     * 根据实体树编码同步其所有子节点编码
     * @param entity      需要同步子节点编码的节点
     * @param oldTreeCode 原有的实体树编码
     */
    <T extends ITreeEntity> void synchronizeSubTree(T entity, String oldTreeCode);

    /**
     * 树形实体更改上级后的处理
     * @param entity     树形实体
     * @param parentCode 新的上级编码
     * @return ITreeEntity 树实体对象
     */
    <T extends ITreeEntity> T afterMoveNodeToParent(T entity, String parentCode);
}
