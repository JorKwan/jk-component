package com.persagy.yc.common.model.entity;

/**
 * 树形接口
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
public interface ITreeEntity extends IBaseEntity {

    /** 实体属性树形编码 */
    String PROP_INNER_CODE = "innerCode";

    /**
     * 取得树形编码
     * @return 树形编码
     */
    String getInnerCode();

    /**
     * 设置树形编码
     * @param innerCode 设置树形编码
     */
    void setInnerCode(String innerCode);

    /**
     * 取得innercode属性名
     * @return
     */
    String getInnerCodeField();

    /**
     * 取得当前实体表名
     * @return 表名
     */
    String getTreeTableName();
}
