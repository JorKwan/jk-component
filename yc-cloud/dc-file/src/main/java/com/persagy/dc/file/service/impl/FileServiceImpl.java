package com.persagy.dc.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.persagy.dc.file.dao.FileMapper;
import com.persagy.dc.file.model.FileInfo;
import com.persagy.dc.file.model.FileInfoCreator;
import com.persagy.dc.file.service.IFileService;
import com.persagy.yc.common.constant.ValidEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件服务实现类
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Service
@Transactional
public class FileServiceImpl extends ServiceImpl<FileMapper, FileInfo> implements IFileService {

    @Override
    public FileInfo insertFile(FileInfo vo) {
        ensureFilePath(vo);
        save(vo);
        return vo;
    }

    @Override
    public FileInfo updateFile(FileInfo vo) {
        FileInfo dbVO = load(vo.getId());
        vo.setCreationTime(dbVO.getCreationTime());
        ensureFilePath(vo);
        updateById(vo);
        return vo;
    }

    @Override
    public List<FileInfo> batchInsert(List<FileInfo> voList) {
        voList.forEach(vo -> ensureFilePath(vo));
        saveBatch(voList);
        return voList;
    }

    @Override
    public void deleteFile(FileInfo vo) {
        vo.setValid(ValidEnum.FALSE.getType());
        updateFile(vo);
    }

    @Override
    public FileInfo load(String id) {
        return getById(id);
    }

    @Override
    public List<FileInfo> queryByBusinessId(String businessId) {
        if(StrUtil.isBlank(businessId)) {
            return null;
        }
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.
                eq(FileInfo::getBusinessId, businessId).
                eq(FileInfo::getValid, ValidEnum.TRUE.getType());
        return list(queryWrapper);
    }

    /**
     * 确认文件名称
     * @param vo 文件管理对象
     * @return
     */
    private void ensureFilePath(FileInfo vo) {
        // 组装文件路径 - 日期以文件创建日期为准
        FileInfoCreator.buildFilePath(vo);
        // 是否存在同名
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.
                eq(FileInfo::getValid, ValidEnum.TRUE.getType()).
                eq(FileInfo::getFilePath, vo.getFilePath());
        if(StrUtil.isNotBlank(vo.getId())) {
            queryWrapper.ne(FileInfo::getId, vo.getId());
        }
        List<FileInfo> dbVOs = list(queryWrapper);
        if(CollUtil.isEmpty(dbVOs)) {
            return;
        }
        // 存在同名 - 重新命名 +随机数
        vo.setFileName(resetFileName(vo.getFileName()));
        ensureFilePath(vo);
    }

    /**
     * 重命名
     * @param oldFileName 原文件名
     * @return 原文件名前缀+"-随机数"+后缀
     */
    private String resetFileName(String oldFileName) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(FileUtil.getPrefix(oldFileName)).append("-");
        fileName.append(RandomUtil.randomInt(100000)).append(".");
        fileName.append(FileUtil.getSuffix(oldFileName));
        return fileName.toString();
    }
}
