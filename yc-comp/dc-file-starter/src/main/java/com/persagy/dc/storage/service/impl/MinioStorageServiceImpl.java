package com.persagy.dc.storage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.persagy.dc.storage.constant.FileCommonConst;
import com.persagy.dc.storage.service.IFileStorageService;
import com.persagy.yc.common.exception.BusinessException;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * MinIO存储服务实现类
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Service
@Slf4j
public class MinioStorageServiceImpl implements IFileStorageService {

    @Resource
    private MinioClient minioClient;

    @Override
    public void upload(String bucketName, String fileName, InputStream input) {
        // 容错判断
        if(StrUtil.isBlank(fileName) || input == null) {
            throw new BusinessException("上传文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 上传文件
            PutObjectOptions options = new PutObjectOptions(input.available(), -1);
            minioClient.putObject(bucketName, fileName, input, options);
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
    }

    @Override
    public InputStream download(String bucketName, String fileName) {
        // 容错判断
        if(StrUtil.isBlank(fileName)) {
            throw new BusinessException("下载文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 下载文件
            return minioClient.getObject(bucketName, fileName);
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
        return null;
    }

    @Override
    public String fetchUrl(String bucketName, String fileName) {
        // 容错判断
        if(StrUtil.isBlank(fileName)) {
            throw new BusinessException("下载文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 下载文件
            return minioClient.presignedGetObject(bucketName, fileName, FileCommonConst.URL_EXPIRES);
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
        return null;
    }

    @Override
    public boolean exists(String bucketName, String fileName) {
        // 容错判断
        if(StrUtil.isBlank(fileName)) {
            throw new BusinessException("检查文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 查看文件元数据
            ObjectStat stat = minioClient.statObject(bucketName, fileName);
            return stat != null;
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
        return false;
    }

    @Override
    public void delete(String bucketName, String fileName) {
        // 容错判断
        if(StrUtil.isBlank(fileName)) {
            throw new BusinessException("删除文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 删除文件
            minioClient.removeObject(bucketName, fileName);
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
    }

    @Override
    public void deletePath(String bucketName) {
        // 容错判断 桶名为空时也不让删
        if(StrUtil.isBlank(bucketName)) {
            throw new BusinessException("删除文件参数有误！");
        }
        try {
            // 确认桶是否存在
            bucketName = ensureBucket(bucketName);
            // 删除桶
            minioClient.removeBucket(bucketName);
        } catch (Exception e) {
            MinioExceptionHandler.handleException(e);
        }
    }

    /**
     * 桶ensure
     * @param bucketName 桶名
     * @return 确认后的桶名
     * @throws Exception
     */
    private String ensureBucket(String bucketName) throws Exception{
        // 桶名为空时，使用默认桶
        if(StrUtil.isBlank(bucketName)) {
            bucketName = FileCommonConst.DEFAULT_BUCKET;
        }
        log.info("开始检查桶名：[{0}]是否存在", bucketName);
        boolean isExist = minioClient.bucketExists(bucketName);
        if(isExist) {
            log.info("桶名：[{0}]已存在", bucketName);
            return bucketName;
        }
        minioClient.makeBucket(bucketName);
        log.info("桶名：[{0}]不存在，创建成功", bucketName);
        return bucketName;
    }
}
