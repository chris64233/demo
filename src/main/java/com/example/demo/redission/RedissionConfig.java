package com.example.demo.redission;

import cn.hutool.core.util.StrUtil;
import com.example.demo.task.once.OnceTask;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.redission.CommonConstant.ROOT_NAME;

/**
 * @author fands
 * @Title: RedissionConfig
 * @Package com.zmdms.common.config
 * @Description: redissonn配置
 * @date 2024/11/6 14:36
 * @Version 1.0.0
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = ROOT_NAME, name = "redission", havingValue = "open")
public class RedissionConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.database}")
    private Integer database;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec()); // 设置全局使用的序列化器
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + port);
        singleServerConfig.setDatabase(null == database ? 0 : database);
        if (StrUtil.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    @Bean
    OnceTask onceTask(RedissonClient redissonClient) {
        return new OnceTask(redissonClient);
    }
}
