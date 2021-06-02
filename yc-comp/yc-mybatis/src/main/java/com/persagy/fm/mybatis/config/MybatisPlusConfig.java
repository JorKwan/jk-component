package com.persagy.fm.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * MybatisPlusConfig
 * </p>
 *
 * @author lixing
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-09-09 09:13:27
 **/
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * @param
     * @description:配置分页插件 不配置分页插件，MP提供的分页方法会查询全部，分页不生效
     * 使用实例：
     * IPage<Member> memberPage = new Page<>(1, 2，false) true查询总记录数量
     * @exception:
     * @author: LuoGuangyi
     * @company: Persagy Technology Co.,Ltd
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @since: 2020/09/30 10:33
     * @version: V1.0
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
