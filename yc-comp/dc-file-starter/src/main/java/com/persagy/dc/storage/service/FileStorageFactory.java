package com.persagy.dc.storage.service;

import com.persagy.dc.storage.constant.FileStorageEnum;
import com.persagy.yc.common.helper.SpringHelper;

/**
 * 文件存储管理工厂
 * @author Charlie Yu
 * @date 2021-05-15
 */
public class FileStorageFactory {

    private static IFileStorageService storageService;

    /**
     * 获取文件存储服务
     * @return
     */
    public static IFileStorageService getService() {
        if(storageService == null) {
            String storageType = SpringHelper.getString("persagy.dc.file.storage", "0");
            String beanName = FileStorageEnum.load(storageType).getName();
            storageService = SpringHelper.getBean(beanName);
        }
        return storageService;
    }
}
