package com.persagy.dc.message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.persagy.dc.message.model.MessageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息 dao
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Mapper
public interface MessageMapper extends BaseMapper<MessageVO> {
}
