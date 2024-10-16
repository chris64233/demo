package com.example.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置消息队列，路由、监听消费信息
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host:/}")
    private String virtualHost;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }


    /**
     * 声明交换机
     */
    @Bean
    public TopicExchange testExchange() {
        return new TopicExchange(MqConstant.TEST_EXCHANGE);
    }


    /**
     * 声明消息队列
     */
    @Bean
    public Queue testQueue() {
        Map<String, Object> arguments = new HashMap<>(8);
        return new Queue(MqConstant.TEST_QUEUE, true, false, false, arguments);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with(MqConstant.TEST_ROUTING_KEY);
    }

    /**
     * 声明交换机
     */
    @Bean
    public DirectExchange zfExchange() {
        return new DirectExchange(MqConstant.ZF_EXCHANGE);
    }


    /**
     * 声明消息队列
     */
    @Bean
    public Queue zfQueue() {
        Map<String, Object> arguments = new HashMap<>(8);
        return new Queue(MqConstant.ZF_QUEUE, true, false, false, arguments);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding zfBinding() {
        return BindingBuilder.bind(zfQueue()).to(zfExchange()).with(MqConstant.ZF_ROUTING_KEY);
    }

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

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
}
