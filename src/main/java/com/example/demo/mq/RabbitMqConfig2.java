package com.example.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置消息队列，路由、监听消费信息
 */
@Configuration
@Slf4j
public class RabbitMqConfig2 {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                    log.info("correlationData:{}, ack:{}, cause:{}", correlationData, ack, cause);
                    if (ack && null != correlationData) {
                        String messageId = correlationData.getId();
                        log.info("消息已正确投递到交换机, messageId:{}", messageId);
                    } else {
                        log.error("消息投递至交换机失败,correlationData:{}，cause:{}", correlationData, cause);
                    }
                }
        );
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("消息无法路由,message:{}, replyCode:{}, replyText:{}, exchange:{}, routingKey:{}",
                    message, replyCode, replyText, exchange, routingKey);
        });
        return rabbitTemplate;
    }
}
