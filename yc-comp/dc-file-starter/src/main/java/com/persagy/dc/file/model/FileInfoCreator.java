package com.persagy.dc.file.model;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.persagy.dc.storage.constant.FileCommonConst;
import com.persagy.yc.common.exception.BusinessException;
import com.persagy.yc.common.lang.PsDate;

/**
 * 文件信息组装器
 * @author Charlie Yu
 * @date 2021-05-15
 */
public class FileInfoCreator {

    /** 桶名校验正则 */
    private static final String BUCKET_PATTERN = "^[a-z0-9][a-z0-9\\-]*[a-z0-9]$";

    /**
     * 创建FileInfo
     * @param groupCode 集团编码 - 租户
     * @param businessId 业务Id
     * @param bucketName 桶 - 应用名
     * @param fileName 文件名
     * @return
     */
    public static FileInfo of(String groupCode, String businessId, String bucketName, String fileName){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setGroupCode(groupCode);
        fileInfo.setFileBucket(formatBucket(bucketName));
        fileInfo.setFileName(fileName);
        fileInfo.setFileType("0");
        fileInfo.setBusinessId(businessId);
        return fileInfo;
    }

    /**
     * 桶名处理
     * 将大写转换为小写。
     * 校验字符：小写字母、连字符、数字，长度为[3,63]，开头和结尾必须是字母或数字
     * https://docs.aws.amazon.com/AmazonS3/latest/userguide/bucketnamingrules.html
     * @param bucketName 桶名
     * @return
     */
    public static String formatBucket(String bucketName) {
        if(StrUtil.isBlank(bucketName)) {
            return FileCommonConst.DEFAULT_BUCKET;
        }
        // 格式转换
        bucketName = bucketName.toLowerCase();
        // 校验
        if(StrUtil.length(bucketName) > 63 ||
                !ReUtil.isMatch(BUCKET_PATTERN, bucketName)) {
            throw new BusinessException("桶名有误，允许字符：小写字母、连字符、数字，长度为3至63，开头和结尾必须是字母或数字");
        }
        return bucketName;
    }

    /**
     * 组装文件路径
     * groupCode/dateStr/fileName
     * @param fileInfo 文件管理信息
     */
    public static void buildFilePath(FileInfo fileInfo) {
        String date = PsDate.parsePsDate(fileInfo.getCreationTime()).toString();
        StringBuilder sb = new StringBuilder(fileInfo.getGroupCode());
        sb.append(FileNameUtil.UNIX_SEPARATOR).append(date);
        sb.append(FileNameUtil.UNIX_SEPARATOR).append(fileInfo.getFileName());
        fileInfo.setFilePath(sb.toString());
    }
}
