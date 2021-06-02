package com.persagy;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * springboot服务器
 * 同时启动Eureka
 * @author Charlie Yu
 * @version 1.0 2021-03-02
 */
@EnableCaching(proxyTargetClass = true)
@EnableDiscoveryClient
@Configuration
@EnableSpringUtil
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
