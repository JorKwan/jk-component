package com.persagy.dc.storage.service.impl;

import com.persagy.yc.common.exception.BusinessException;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * MinIO异常处理
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Slf4j
public class MinioExceptionHandler {

    /**
     * Minio异常处理
     * @param e 异常
     */
    public static void handleException(Exception e) {
        log.error(e.getMessage(), e);
        if(e instanceof InvalidKeyException) {
            throw new BusinessException("文件服务器-密钥有误");
        }
        if(e instanceof NoSuchAlgorithmException) {
            throw new BusinessException("文件服务器密钥有误");
        }
        if(e instanceof InvalidResponseException) {
            throw new BusinessException("文件服务器密钥有误");
        }
        if(e instanceof InvalidBucketNameException) {
            throw new BusinessException("文件服务器-不合法的存储桶名称。");
        }
        if(e instanceof ErrorResponseException) {
            throw new BusinessException("文件服务器-执行失败异常。");
        }
        if(e instanceof InsufficientDataException) {
            throw new BusinessException("文件服务器密钥有误");
        }
        if(e instanceof XmlParserException) {
            throw new BusinessException("文件服务器-解析返回的XML异常。");
        }
        if(e instanceof InternalException) {
            throw new BusinessException("文件服务器-内部错误。");
        }
        if(e instanceof IOException) {
            throw new BusinessException("文件服务器-连接异常。");
        }
        throw new BusinessException(e.getMessage(), e);
    }
}
