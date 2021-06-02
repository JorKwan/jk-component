package com.persagy.fm.mybatis.config;

import com.persagy.fm.mybatis.handler.DynamicDataSourceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MyBatis配置
 * @author Charlie Yu
 * @date 2021-03-29
 */
@Configuration
@Order(2)
public class MyBatisWebConfigurer implements WebMvcConfigurer {

    @Bean
    public DynamicDataSourceHandler dynamicDataSourceHandler() {
        return new DynamicDataSourceHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截的路径、不拦截的路径、优先级等等 -- 在contextHandler之后执行
        registry.addInterceptor(dynamicDataSourceHandler()).order(20).addPathPatterns("/**");
    }
}
