package com.persagy.yc.common.context;

/**
 * app上下问工厂类
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
public interface IAppContextFactory {

    /**
     * 创建应用上下文对象
     * @return 应用上下文对象
     */
    AppContext getContext();

    /**
     * 卸载应用上下文对象
     * @return 应用上下文对象
     */
    void unloadContext();
}
