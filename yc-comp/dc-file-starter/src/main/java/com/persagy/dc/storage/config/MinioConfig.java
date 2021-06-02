package com.persagy.dc.storage.config;

import com.persagy.yc.common.exception.BusinessException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO配置
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Configuration
public class MinioConfig {

    /** 文件服务器url */
    @Value("${persagy.dc.file.url}")
    private String url;
    /** 文件服务器accessKey */
    @Value("${persagy.dc.file.accessKey}")
    private String accessKey;
    /** 文件服务器secretKey */
    @Value("${persagy.dc.file.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        try {
            return new MinioClient(url, accessKey, secretKey);
        } catch (MinioException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
