package com.persagy.dc.amqp.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 * 绑定Exchange-RoutingKey-Queue
 * @author Charlie Yu
 * @date 2021-05-20
 */
@EnableRabbit
@Configuration
public class RabbitBindingConfig {

    /**
     * 消息转换器
     * @return
     */
    @Bean
    public MessageConverter customMessageConvert() {
        return new Jackson2JsonMessageConverter();
    }
}
