package com.persagy.dc.file.controller;

import cn.hutool.core.util.StrUtil;
import com.persagy.dc.file.model.FileInfo;
import com.persagy.dc.file.service.IFileService;
import com.persagy.yc.common.constant.ResponseCode;
import com.persagy.yc.common.exception.BusinessException;
import com.persagy.yc.common.model.response.CommonResult;
import com.persagy.yc.common.model.response.PageList;
import com.persagy.yc.common.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 文件管理Controller
 * @author Charlie Yu
 * @date 2021-05-15
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    IFileService fileService;

    @PostMapping("/insert")
    public CommonResult<FileInfo> insert(@RequestBody FileInfo fileInfo) {
        if(StrUtil.isBlank(fileInfo.getFileName()) ||
                StrUtil.isBlank(fileInfo.getGroupCode()) ||
                StrUtil.isBlank(fileInfo.getFileBucket())) {
            throw new BusinessException(ResponseCode.A0400.getCode(), ResponseCode.A0400.getDesc());
        }
        fileInfo.setCreationTime(new Date());
        fileInfo = fileService.insertFile(fileInfo);
        return ResultHelper.single(fileInfo);
    }

    @PostMapping("/update")
    public CommonResult<FileInfo> update(@RequestBody FileInfo fileInfo) {
        if(StrUtil.isBlank(fileInfo.getId()) ||
                StrUtil.isBlank(fileInfo.getFileName()) ||
                StrUtil.isBlank(fileInfo.getGroupCode()) ||
                StrUtil.isBlank(fileInfo.getFileBucket())) {
            throw new BusinessException(ResponseCode.A0400.getCode(), ResponseCode.A0400.getDesc());
        }
        fileInfo.setModifiedTime(new Date());
        fileInfo = fileService.updateFile(fileInfo);
        return ResultHelper.single(fileInfo);
    }

    @PostMapping("/delete")
    public CommonResult<String> delete(@RequestBody String id) {
        if(StrUtil.isBlank(id)) {
            throw new BusinessException(ResponseCode.A0400.getCode(), ResponseCode.A0400.getDesc());
        }
        FileInfo fileInfo = fileService.load(id);
        if(fileInfo == null) {
            throw new BusinessException("找不到对应的文件信息");
        }
        fileService.deleteFile(fileInfo);
        return ResultHelper.success();
    }

    @PostMapping("/load")
    public CommonResult<FileInfo> load(@RequestBody String id) {
        if(StrUtil.isBlank(id)) {
            throw new BusinessException(ResponseCode.A0400.getCode(), ResponseCode.A0400.getDesc());
        }
        FileInfo fileInfo = fileService.load(id);
        if(fileInfo == null) {
            throw new BusinessException("找不到对应的文件信息");
        }
        return ResultHelper.single(fileInfo);
    }

    @PostMapping("/queryByBusiness")
    public CommonResult<PageList<FileInfo>> queryByBusiness(@RequestBody String businessId) {
        if(StrUtil.isBlank(businessId)) {
            throw new BusinessException(ResponseCode.A0400.getCode(), ResponseCode.A0400.getDesc());
        }
        List<FileInfo> voList = fileService.queryByBusinessId(businessId);
        return ResultHelper.multi(voList);
    }
}
