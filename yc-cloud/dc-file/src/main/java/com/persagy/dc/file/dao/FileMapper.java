package com.persagy.dc.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.persagy.dc.file.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件Dao
 * @author Charlie Yu
 * @date 2021-05-15
 */
@Mapper
public interface FileMapper extends BaseMapper<FileInfo> {
}
