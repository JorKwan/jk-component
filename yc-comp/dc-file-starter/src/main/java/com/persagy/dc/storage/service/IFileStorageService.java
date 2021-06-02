package com.persagy.dc.storage.service;

import java.io.InputStream;

/**
 * 文件存储服务接口
 * @author Charlie Yu
 * @date 2021-05-15
 */
public interface IFileStorageService {

    /**
     * 文件上传
     * @param bucketName 桶名
     * @param fileName 文件名 - 同名文件则覆盖
     * @param input 输入流
     */
    void upload(String bucketName, String fileName, InputStream input);

    /**
     * 文件下载
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return
     */
    InputStream download(String bucketName, String fileName);

    /**
     * 获取下载Url
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return
     */
    String fetchUrl(String bucketName, String fileName);

    /**
     * 文件是否存在
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return
     */
    boolean exists(String bucketName, String fileName);

    /**
     * 删除文件
     * @param bucketName 桶名
     * @param fileName 文件名
     */
    void delete(String bucketName, String fileName);

    /**
     * 删除桶
     * @param bucketName 桶名
     */
    void deletePath(String bucketName);
}
