package com.persagy.dc.file.service;

import com.persagy.dc.file.model.FileInfo;

import java.util.List;

/**
 * 文件管理接口
 * @author Charlie Yu
 * @date 2021-05-15
 */
public interface IFileService {

    /**
     * 保存
     * @param vo
     * @return
     */
    FileInfo insertFile(FileInfo vo);

    /**
     * 保存
     * @param vo
     * @return
     */
    FileInfo updateFile(FileInfo vo);

    /**
     * 批量保存
     * @param voList
     * @return
     */
    List<FileInfo> batchInsert(List<FileInfo> voList);

    /**
     * 删除
     * @param vo
     */
    void deleteFile(FileInfo vo);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    FileInfo load(String id);

    /**
     * 通过业务ID查询
     * @param businessId
     * @return
     */
    List<FileInfo> queryByBusinessId(String businessId);
}
