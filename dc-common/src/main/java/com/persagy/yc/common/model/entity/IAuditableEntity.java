package com.persagy.yc.common.model.entity;

import java.util.Date;

/**
 * 审计信息类接口
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
public interface IAuditableEntity extends IBaseEntity {

	/**
	 * 取得创建时间
	 * @return 创建时间
	 */
	Date getCreationTime();

	/**
	 * 设置创建时间,记录插入时有效
	 * @param creationtime 创建时间
	 */
	void setCreationTime(Date creationtime);

	/**
	 * 取得创建实体的操作员的登录名
	 * @return 操作员标识
	 */
	String getCreator() ;

	/**
	 * 设置创建实体的操作员标识，记录插入时有效
	 * @param creator 操作员标识
	 */
	void setCreator(String creator);

	/**
	 * 取得最后修改时间.
	 * @return 最后修改时间
	 */
	Date getModifiedTime();

	/**
	 * 设置最后修改时间.记录更新时有效
	 * @param modifiedtime 最后修改时间
	 */
	void setModifiedTime(Date modifiedtime);

	/**
	 * 取得最后修改的操作员的登录名.
	 * @return 操作员的登录名.
	 */
	String getModifier();

	/**
	 * 设置最后修改的操作员的登录名,记录更新时有效
	 * @param modifier 操作员的登录名.
	 */
	void setModifier(String modifier);
}
