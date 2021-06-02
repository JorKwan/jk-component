package com.persagy.dc.sender.service;

import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.model.SenderRegister;

/**
 * 消息 执行接口
 * @author Charlie Yu
 * @date 2021-05-24
 */
public interface IMessageSender {

    /**
     * 初始化消息处理器参数
     * @param register
     * @return 是否初始化成功
     */
    boolean initParam(SenderRegister register);

    /**
     * 发送消息
     * @param vo
     */
    void send(MessageVO vo);
}
