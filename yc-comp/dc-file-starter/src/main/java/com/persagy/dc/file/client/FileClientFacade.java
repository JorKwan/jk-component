package com.persagy.dc.file.client;

import cn.hutool.extra.spring.SpringUtil;
import com.persagy.dc.file.model.FileInfo;
import com.persagy.yc.common.model.response.CommonResult;
import com.persagy.yc.common.utils.ResultHelper;

/**
 * 文件管理Client门面
 * @author Charlie Yu
 * @date 2021-05-15
 */
public class FileClientFacade {

    private static FileManageClient client = SpringUtil.getBean(FileManageClient.class);

    /**
     * 新增
     * @param fileInfo
     * @return
     */
    public static FileInfo insert(FileInfo fileInfo) {
        CommonResult<FileInfo> result = client.insert(fileInfo);
        return ResultHelper.getContent(result);
    }

    /**
     * 修改
     * @param fileInfo
     * @return
     */
    public static FileInfo update(FileInfo fileInfo) {
        CommonResult<FileInfo> result = client.update(fileInfo);
        return ResultHelper.getContent(result);
    }

    /**
     * 删除
     * @param fileId
     */
    public static void delete(String fileId) {
        CommonResult<String> result = client.delete(fileId);
        ResultHelper.getContent(result);
    }

    /**
     * 查询
     * @param fileId
     * @return
     */
    public static FileInfo load(String fileId) {
        CommonResult<FileInfo> result = client.load(fileId);
        return ResultHelper.getContent(result);
    }
}
