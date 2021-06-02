package com.persagy.dc.file.model;

import com.persagy.yc.common.lang.PsDate;
import com.persagy.yc.common.model.entity.AuditableEntity;
import lombok.Data;

/**
 * 文件对象
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Data
public class FileInfo extends AuditableEntity {

    /** 集团编码：租户 */
    private String groupCode;
    /** 文件名称:显示用 */
    private String fileName;
    /** 文件类型：0-文件，1-目录 */
    private String fileType;
    /** 文件分组:默认为应用名 */
    private String fileBucket;
    /** 文件路径：含文件名称 - 对应文件存储路径 */
    private String filePath;
    /** 文件大小 */
    private Long fileSize;
    /** 关联业务Id */
    private String businessId;
    /** 保留期限 */
    private PsDate expireDate;
}
