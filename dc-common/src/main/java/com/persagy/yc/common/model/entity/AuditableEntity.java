package com.persagy.yc.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 审计信息父类
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AuditableEntity<T> extends BaseEntity<T> implements IAuditableEntity {

    /** 序列id */
    private static final long serialVersionUID = -6039076670318997447L;

    /** 创建时间 */
    public static final String PROP_CREATIONTIME = "creation_time";
    public static final String DO_PROP_CREATIONTIME = "creationTime";

    /** 创建人 */
    public static final String PROP_CREATOR = "creator";

    /** 最后一次修改时间 */
    public static final String PROP_MODIFIEDTIME = "modified_time";
    public static final String DO_PROP_MODIFIEDTIME = "modifiedTime";

    /** 最后一个修改人 */
    public static final String PROP_MODIFIER = "modifier";

    @JsonIgnore
    protected Date creationTime;

    protected String creator;

    @JsonIgnore
    protected Date modifiedTime;

    protected String modifier;

}
