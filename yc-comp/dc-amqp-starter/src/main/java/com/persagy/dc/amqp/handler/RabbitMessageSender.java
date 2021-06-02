package com.persagy.dc.amqp.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 发送MQ消息
 * 需要配置Binding
 * @author Charlie Yu
 * @date 2021-05-20
 */
@Component
@Slf4j
public class RabbitMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /** 读取配置不发消息的RoutingKey */
    @Value("#{'${persagy.common.amqp.disablekey:?}'.split(',')}")
    private Set<String> disableKeys;

    /**
     * 发送消息
     * @param exchange 发送的交换机
     * @param routingKey 路由Key
     * @param message
     */
    public void send(String exchange, String routingKey, String message) {
        log.info("开始发送消息,routingKey={},message={}", routingKey, message);
        // 配置了不发此类消息
        if(CollUtil.isNotEmpty(disableKeys) &&
                StrUtil.isNotBlank(routingKey) &&
                disableKeys.contains(routingKey)) {
            return;
        }
        // 发送 异常不捕获直接抛出
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("消息发送成功,routingKey={}", routingKey);
    }
}
