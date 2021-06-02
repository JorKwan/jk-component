package com.persagy.yc.common.model.entity;


import java.util.Date;

/**
 * 基础实体接口
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
public interface IBaseEntity extends Comparable<IBaseEntity>{

	/**
	 * 取得实体主键
	 * @return 实体主键
	 */
	String getId();

	/**
	 * 设置实体主键
	 * @param id 实体主键
	 */
	void setId(String id);

	/**
	 * 取得时间戳
	 * @return 时间戳
	 */
	Date getTs();

	/**
	 * 设置时间戳
	 * @param ts 时间戳
	 */
	void setTs(Date ts);

	/**
	 * 取得实体状态
	 * @return 实体状态
	 */
	Integer getValid();

	/**
	 * 设置实体状态
	 * @param status 实体状态
	 */
	void setValid(Integer status);
	
}
