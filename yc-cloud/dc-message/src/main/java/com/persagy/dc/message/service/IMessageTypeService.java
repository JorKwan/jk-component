package com.persagy.dc.message.service;

import com.persagy.dc.message.model.MessageType;

/**
 * 消息类型 服务
 * @author Charlie Yu
 * @date 2021-05-24
 */
public interface IMessageTypeService {

    /**
     * 新增
     * @param vo
     * @return
     */
    MessageType insertType(MessageType vo);

    /**
     * 修改
     * @param vo
     * @return
     */
    MessageType updateType(MessageType vo);

    /**
     * 删除
     * @param id
     */
    void deleteType(String id);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    MessageType load(String id);
}
