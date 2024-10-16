package com.example.demo.mq;

import com.example.demo.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * rabbitmq消息发送
 */
@Component
@Slf4j
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, String messageBody) {
        String msg = messageBody;
        log.info("send():exchange:{}, routingKey:{}, payload{}", exchange, routingKey, msg);
        try {
            //设置消息属性
            String messageId = UUID.randomUUID().toString();
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.setMessageId(messageId);
            Message message = new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties);
            //发送
            rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData(messageId));
            log.info("发送消息，消息ID:{}", messageId);
        } catch (Exception e) {
            throw new RuntimeException("发送RabbitMQ消息失败！", e);
        }
    }

}
