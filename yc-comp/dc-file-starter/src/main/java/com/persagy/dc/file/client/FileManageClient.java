package com.persagy.dc.file.client;

import com.persagy.dc.file.model.FileInfo;
import com.persagy.dc.storage.config.FileFeignConfig;
import com.persagy.yc.common.model.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 文件管理Client
 * @author Charlie Yu
 * @date 2021-05-15
 */
@FeignClient(name = "dc-file", configuration = FileFeignConfig.class)
public interface FileManageClient {

    /**
     * 新增文件信息
     * @param fileInfo 文件信息
     * @return 文件信息
     */
    @PostMapping("/file/insert")
    CommonResult<FileInfo> insert(@RequestBody FileInfo fileInfo);

    /**
     * 修改文件信息
     * @param fileInfo 文件信息
     * @return 文件信息
     */
    @PostMapping("/file/update")
    CommonResult<FileInfo> update(@RequestBody FileInfo fileInfo);

    /**
     * 删除文件信息
     * @param id 文件信息
     * @return 文件信息
     */
    @PostMapping("/file/delete")
    CommonResult<String> delete(@RequestBody String id);

    /**
     * 查询文件信息
     * @param id 文件信息
     * @return 文件信息
     */
    @PostMapping("/file/load")
    CommonResult<FileInfo> load(@RequestBody String id);
}
