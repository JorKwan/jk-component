package com.persagy.dc.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.persagy.dc.message.dao.MessageTypeMapper;
import com.persagy.dc.message.model.MessageType;
import com.persagy.dc.message.service.IMessageTypeService;
import com.persagy.yc.common.constant.ValidEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息类型 服务实现
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Service
@Transactional
public class MessageTypeServiceImpl extends ServiceImpl<MessageTypeMapper, MessageType> implements IMessageTypeService {

    @Override
    public MessageType insertType(MessageType vo) {
        save(vo);
        return vo;
    }

    @Override
    public MessageType updateType(MessageType vo) {
        updateById(vo);
        return vo;
    }

    @Override
    public void deleteType(String id) {
        MessageType vo = load(id);
        vo.setValid(ValidEnum.FALSE.getType());
        updateType(vo);
    }

    @Override
    public MessageType load(String id) {
        return getById(id);
    }
}
