package com.persagy.dc.message.service;

import com.persagy.dc.message.model.MessageVO;

/**
 * 消息 服务
 * @author Charlie Yu
 * @date 2021-05-24
 */
public interface IMessageService {

    /**
     * 新增
     * @param vo
     * @return
     */
    MessageVO insertMessage(MessageVO vo);

    /**
     * 修改
     * @param vo
     * @return
     */
    MessageVO updateMessage(MessageVO vo);

    /**
     * 删除
     * @param id
     */
    void deleteMessage(String id);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    MessageVO load(String id);
}
