package com.persagy.dc.message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.persagy.dc.message.model.MessageType;
import com.persagy.dc.message.model.SenderRegister;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息渠道注册 dao
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Mapper
public interface SenderRegisterMapper extends BaseMapper<SenderRegister> {
}
