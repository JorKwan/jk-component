package com.persagy.yc.common.context;

import lombok.Data;

/**
 * 默认应用上下文
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
@Data
public class DefaultAppContext extends AppContext {

    /**
     * 用户主键
     */
    private String accountId;

//    /**
//     * 用户名
//     */
//    private String userName;
//
//    /**
//     * 租户
//     */
//    private String tenant;
//
//    /**
//     * 所属产品线
//     */
//    private String productLine;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    private String projectId;
    private String appId;
    private String groupCode;
}
