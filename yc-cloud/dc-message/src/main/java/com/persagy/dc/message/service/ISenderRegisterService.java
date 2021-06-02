package com.persagy.dc.message.service;

import com.persagy.dc.message.model.SenderRegister;

import java.util.List;

/**
 * 消息渠道 接口
 * @author Charlie Yu
 * @date 2021-05-26
 */
public interface ISenderRegisterService {

    /**
     * 查询所有消息渠道
     * @return
     */
    List<SenderRegister> queryAll();
}
