package com.persagy.dc.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.persagy.dc.message.dao.MessageMapper;
import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.service.IMessageService;
import com.persagy.yc.common.constant.ValidEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息 服务实现
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageVO> implements IMessageService {
    @Override
    public MessageVO insertMessage(MessageVO vo) {
        save(vo);
        return vo;
    }

    @Override
    public MessageVO updateMessage(MessageVO vo) {
        updateById(vo);
        return vo;
    }

    @Override
    public void deleteMessage(String id) {
        MessageVO vo = load(id);
        vo.setValid(ValidEnum.FALSE.getType());
        updateMessage(vo);
    }

    @Override
    public MessageVO load(String id) {
        return getById(id);
    }
}
