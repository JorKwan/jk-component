package com.persagy.dc.sender.service.impl;

import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.model.SenderRegister;
import com.persagy.dc.sender.service.IMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 邮件消息
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Component
public class MailMessageSender implements IMessageSender {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public boolean initParam(SenderRegister register) {
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.smtp.socketFactory.port", "465");
        javaMailProperties.setProperty("mail.smtp.port", "465");
        mailSender.setJavaMailProperties(javaMailProperties);
        return false;
    }

    @Override
    public void send(MessageVO vo) {

    }
}
