package com.example.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

/**
 * 冻结消息消费
 *
 * @author zengyizeng
 */
//@Component
@Slf4j
public class TestConsumer2 implements ChannelAwareMessageListener {

    /**
     * 消费冻结任务待处理消息
     */
    @RabbitListener(queues = MqConstant.ZF_QUEUE)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String ueBizMsgStr = new String(String.valueOf(message));
        JSONObject ueBizMsgJsonTask = JSONObject.parseObject(ueBizMsgStr);
        // 消息的唯一标识id
        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();
        String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("接收到消息，当前消息ID:{} ", messageProperties.getMessageId());
        try {
            log.info(messageStr);
            // 成功的回执
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费冻结消息失败，messageId={}", messageProperties.getMessageId(), e);
        }
    }

}
