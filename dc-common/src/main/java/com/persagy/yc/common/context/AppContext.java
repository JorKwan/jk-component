package com.persagy.yc.common.context;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


/**
 * 应用访问上下文类，主要负责上下文的属性和参数的操作
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
@Slf4j
public abstract class AppContext implements Serializable {

    /** 序列id */
    private static final long serialVersionUID = 697592850956445675L;

    /** 工厂对象 */
    private static IAppContextFactory factory = null;

    /**
     * 取得当前线程中的上下文对象
     * @return 上下文对象
     */
    public static AppContext getContext() {
        return getFactory().getContext();
    }

    /**
     * 反注册当前上下文对象
     */
    public static void unload() {
        getFactory().unloadContext();
    }

    /**
     * 取得上下文工厂类
     * @return 上下文工厂类
     */
    public static IAppContextFactory getFactory() {
        if (factory == null) {
            factory = new DefaultAppContextFactory();
        }
        return factory;
    }

//    /**
//     * 取得操作用户主键
//     * @return 用户主键
//     */
//    public abstract String getUserId();
//
//    /**
//     * 设置操作用户主键
//     * @param userId 用户主键
//     */
//    public abstract void setUserId(String userId);
//
//    /**
//     * 取得操作用户名
//     * @return 用户名
//     */
//    public abstract String getUserName();
//
//    /**
//     * 设置操作用户名
//     * @param userName 用户名
//     */
//    public abstract void setUserName(String userName);
//
//    /**
//     * 取得租户
//     * @return 租户
//     */
//    public abstract String getTenant();
//
//    /**
//     * 设置租户
//     * @param tenant 租户
//     */
//    public abstract void setTenant(String tenant);
//
//    /**
//     * 取得产品线
//     * @return 产品线
//     */
//    public abstract String getProductLine();
//
//    /**
//     * 设置产品线
//     * @param productLine 产品线
//     */
//    public abstract void setProductLine(String productLine);

    /**
     * 动态数据源支持
     * @return java.lang.String
     **/
    public abstract String getDataSourceName();

    /**
     * 设置动态数据源
     * @param dataSourceName 动态数据源
     * @return void
     **/
    public abstract void setDataSourceName(String dataSourceName);

    public abstract void setAccountId(String accountId);
    public abstract void setProjectId(String projectId);
    public abstract void setGroupCode(String groupCode);
    public abstract void setAppId(String appId);
    public abstract String getAccountId();
    public abstract String getProjectId();
    public abstract String getGroupCode();
    public abstract String getAppId();
}
