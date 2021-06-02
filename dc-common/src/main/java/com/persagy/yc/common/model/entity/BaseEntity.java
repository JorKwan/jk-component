package com.persagy.yc.common.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
@Data
public abstract class BaseEntity<T> implements IBaseEntity,Serializable,Cloneable{

	/** 序列id */
	private static final long serialVersionUID = -3231114188611235386L;

	/** 实体属性主键id */
	public final static String PROP_ID = "id";

	/** 实体属性版本 */
	public final static String PROP_TS = "ts";

	/** 实体属性实体状态 */
	public final static String PROP_VALID = "valid";

	/** hash码 */
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private int hashCode = Integer.MIN_VALUE;

	/** 实体类必须有的属性 */
	protected String id;

	/** 时间戳 */
	protected Date ts;

	/** 实体状态 - 有效标识 */
	protected Integer valid;

	@Override
	public boolean equals (Object obj) {
		if (null == obj) {
            return false;
        }
		if (!(obj instanceof BaseEntity)) {
			return false;
		} else {
			BaseEntity entity = (BaseEntity) obj;
			if (null == this.getId() || null == entity.getId()) {
				return false;
			} else {
				return (this.getId().equals(entity.getId()));
			}
		}
	}

	@Override
	public int hashCode () {
		if (Integer.MIN_VALUE != this.hashCode) {
			return this.hashCode;
		}
		if (null == this.getId()) {
			return super.hashCode();
		}
		String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
		this.hashCode = hashStr.hashCode();
		return this.hashCode;
	}

	@Override
	public int compareTo (IBaseEntity obj) {
		if (obj.hashCode() > hashCode()) {
			return 1;
		} else if (obj.hashCode() < hashCode()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
