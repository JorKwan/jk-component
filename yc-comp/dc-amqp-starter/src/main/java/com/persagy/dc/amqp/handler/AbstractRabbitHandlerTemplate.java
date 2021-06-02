package com.persagy.dc.amqp.handler;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.io.IOException;

/**
 * RabbitMQ消息Handler 模板类
 * 继承模板类实现handler方法实现业务，并添加RabbitListener注解即可接收队列中的消息
 * @author Charlie Yu
 * @date 2021-05-20
 */
@Slf4j
public abstract class AbstractRabbitHandlerTemplate {

    /**
     * 消息处理
     * 公共拦截，处理消息确认、异常处理
     * @param message 消息体字符串
     * @param channel 通道对象
     * @param vo     消息对象
     */
    @RabbitHandler
    public void process(String message, Channel channel, Message vo) {
        log.info("启动消息处理：{}", message);
        try {
            handler(message, channel, vo);
            // 手动确认消息已消费
            channel.basicAck(vo.getMessageProperties().getDeliveryTag(), false);
            log.info("消息处理成功，{}", message);
        } catch (Exception e) {
            try {
                log.error("消息处理失败：{}", message, e);
                Boolean redelivered = vo.getMessageProperties().getRedelivered();
                // 消息重复处理失败
                if (redelivered) {
                    // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                    channel.basicReject(vo.getMessageProperties().getDeliveryTag(), false);
                    // TODO 后续考虑写入DB，扩展运维手动处理失败消息
                    // ...
                } else {
                    // 如果是第一次失败则再次放入队列 - requeue为是否重新回到队列，true重新入队
                    channel.basicNack(vo.getMessageProperties().getDeliveryTag(), false, true);
                }
                log.error("消息入队成功：{}", message);
            } catch (IOException e1) {
                log.error("消息入队失败：{}", message, e1);
            }
        }
    }

    /**
     * 消息处理
     * 公共拦截，处理消息确认、异常处理
     * @param body 消息体
     * @param channel 通道对象
     * @param vo     消息对象
     */
    @RabbitHandler
    public void process(byte[] body, Channel channel, Message vo) {
        String message = StringUtils.newString(body, Charsets.UTF_8.name());
        process(message, channel, vo);
    }

    /**
     * 消息处理
     * 只需要做实际的业务处理
     * @param message 消息体字符串
     * @param channel 通道对象
     * @param vo     消息对象
     */
    protected abstract void handler(String message, Channel channel, Message vo);
}
