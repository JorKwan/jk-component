package com.persagy.dc.storage.utils;

import com.persagy.dc.file.client.FileClientFacade;
import com.persagy.dc.file.model.FileInfo;
import com.persagy.dc.storage.service.FileStorageFactory;
import com.persagy.yc.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件存储助手
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Slf4j
public class FileStorageHelper {

    /**
     * 上传文件
     * @param fileInfo
     * @param in
     * @return
     */
    public static String uploadFile(FileInfo fileInfo, InputStream in) {
        fileInfo.setFileSize(getStreamSize(in));
        fileInfo = FileClientFacade.insert(fileInfo);
        FileStorageFactory.getService().upload(fileInfo.getFileBucket(), fileInfo.getFilePath(), in);
        return fileInfo.getId();
    }

    /**
     * 上传图片
     * @param fileInfo
     * @param in
     * @return
     */
    public static String uploadImage(FileInfo fileInfo, InputStream in) {
        // 图片压缩
        InputStream cutIn = ImageHelper.cutImage(in);
        return uploadFile(fileInfo, cutIn);
    }

    /**
     * 修改文件
     * @param fileId
     * @param fileName
     * @param in
     * @return
     */
    public static String replaceFile(String fileId, String fileName, InputStream in) {
        FileInfo fileInfo = FileClientFacade.load(fileId);
        // 先删除文件
        FileStorageFactory.getService().delete(fileInfo.getFileBucket(), fileInfo.getFilePath());
        // 更新信息后上传
        fileInfo.setFileName(fileName);
        fileInfo.setFileSize(getStreamSize(in));
        fileInfo = FileClientFacade.update(fileInfo);
        FileStorageFactory.getService().upload(fileInfo.getFileBucket(), fileInfo.getFilePath(), in);
        return fileInfo.getId();
    }

    /**
     * 更新图片
     * @param fileId
     * @param fileName
     * @param in
     * @return
     */
    public static String replaceImage(String fileId, String fileName, InputStream in) {
        // 图片压缩
        InputStream cutIn = ImageHelper.cutImage(in);
        return replaceFile(fileId, fileName, cutIn);
    }

    /**
     * 删除文件及图片
     * @param fileId
     */
    public static void deleteFile(String fileId) {
        FileInfo fileInfo = FileClientFacade.load(fileId);
        FileClientFacade.delete(fileId);
        FileStorageFactory.getService().delete(fileInfo.getFileBucket(), fileInfo.getFilePath());
    }

    /**
     * 下载
     * @param fileId 文件id
     * @return 文件下载地址
     */
    public static String downloadUrl(String fileId) {
        FileInfo fileInfo = FileClientFacade.load(fileId);
        return FileStorageFactory.getService().fetchUrl(fileInfo.getFileBucket(), fileInfo.getFilePath());
    }

    /**
     * 下载
     * @param fileId 文件id
     * @return 文件流
     */
    public static InputStream downloadStream(String fileId) {
        FileInfo fileInfo = FileClientFacade.load(fileId);
        return FileStorageFactory.getService().download(fileInfo.getFileBucket(), fileInfo.getFilePath());
    }

    /**
     * 下载
     * @param fileId 文件id
     * @return 文件内容
     */
    public static byte[] downloadContent(String fileId) {
        // TODO 暂不提供，依赖前端显示规则
        return null;
    }

    /**
     * 获取文件大小
     * @param stream
     * @return
     */
    private static Long getStreamSize(InputStream stream) {
        try {
            return Long.valueOf(stream.available());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("文件上传失败！");
        }
    }
}
