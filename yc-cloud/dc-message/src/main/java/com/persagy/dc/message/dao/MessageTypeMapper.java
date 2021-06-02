package com.persagy.dc.message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.persagy.dc.message.model.MessageType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息类型 dao
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Mapper
public interface MessageTypeMapper extends BaseMapper<MessageType> {
}
