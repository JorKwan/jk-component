package com.persagy.dc.storage.constant;

import lombok.Getter;

/**
 * 文件管理服务
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Getter
public enum FileStorageEnum {
    /** MinIO */
    MINIO("0", "minioStorageServiceImpl"),
    /** FastDFS */
    FAST_DFS("1", "fastDfsStorageServiceImpl");

    private String index;
    private String name;

    FileStorageEnum(String index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * 获取Enum
     * @param index
     * @return
     */
    public static FileStorageEnum load(String index) {
        for (FileStorageEnum value: values()) {
            if (value.getIndex().equals(index)) {
                return value;
            }
        }
        return null;
    }
}
