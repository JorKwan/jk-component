package com.persagy.dc.sender.service.impl;

import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.model.SenderRegister;
import com.persagy.dc.sender.service.IMessageSender;
import org.springframework.stereotype.Component;

/**
 * 短信消息
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Component
public class SmsMessageSender implements IMessageSender {

    @Override
    public boolean initParam(SenderRegister register) {
        return false;
    }

    @Override
    public void send(MessageVO vo) {

    }
}
