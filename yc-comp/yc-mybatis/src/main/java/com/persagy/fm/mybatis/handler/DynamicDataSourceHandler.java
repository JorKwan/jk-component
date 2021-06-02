package com.persagy.fm.mybatis.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.persagy.fm.mybatis.service.IDbService;
import com.persagy.yc.common.context.AppContext;
import com.persagy.yc.common.helper.SpringHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * 动态数据源拦截器
 * @author Charlie Yu
 * @date 2021-03-29
 */
public class DynamicDataSourceHandler extends HandlerInterceptorAdapter {

    /** 忽略的url - swagger的文档不校验 */
    private static final String[] IGNORE_URL = {".html", ".js", ".css", "/swagger-resources"};

    @Autowired
    private DynamicRoutingDataSource dataSource;
    @Autowired
    private DefaultDataSourceCreator dataSourceCreator;
    @Autowired
    private DynamicDataSourceProperties defaultProperty;
    @Autowired
    private IDbService dbService;
    @Value("${spring.datasource.dynamic.enabled:false}")
    private boolean dynamicEnabled;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (StrUtil.containsAny(requestURI, IGNORE_URL)) {
            return true;
        }
        resetDataSource();
        return true;
    }

    /**
     * 多租户处理，数据源切换
     */
    public void resetDataSource() {
        // 是否启用多数据源
        if(!dynamicEnabled) {
            return;
        }
        // 默认为应用名
        String dbNameDefault = SpringHelper.getString("spring.application.name");
        // 创建的数据源名称： 集团编码_应用名
        String newDsName = AppContext.getContext().getGroupCode() + "_" + dbNameDefault;
        // 数据源中是否已存在
        if(!dataSource.getCurrentDataSources().keySet().contains(newDsName)) {
            // 设置新数据源
            DataSourceProperty property = copyProperty(dbNameDefault, newDsName);
            // 创建数据库实例
            dbService.createDataBase(newDsName);
            // 创建数据源
            DataSource currDs = dataSourceCreator.createDataSource(property);
            dataSource.addDataSource(newDsName, currDs);
        }
        // 设置当前数据源
        DynamicDataSourceContextHolder.push(newDsName);
    }

    /**
     * 复制新的数据源
     * @param dbNameDefault 默认连接的实例名
     * @param newDsName 新创建的实例名
     * @return
     */
    private DataSourceProperty copyProperty(String dbNameDefault, String newDsName) {
        // 取默认数据源
        DataSourceProperty srcProperty = defaultProperty.getDatasource().get(defaultProperty.getPrimary());
        // 设置新数据源
        DataSourceProperty property = new DataSourceProperty();
        BeanUtils.copyProperties(srcProperty, property);
        property.setPoolName(newDsName);
        property.setUrl(StrUtil.replace(property.getUrl(), dbNameDefault, newDsName));
        return property;
    }
}
