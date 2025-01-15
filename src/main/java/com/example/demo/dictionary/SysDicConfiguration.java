package com.example.demo.dictionary;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.redission.CommonConstant.ROOT_NAME;


/**
 * @author fands
 * @Title: SysDicConfiguration
 * @Package com.zmdms.common.config
 * @Description: 读取yml文件的字典数据
 * @date 2024/4/28 14:43
 * @Version 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = ROOT_NAME, name = "dic", havingValue = "open")
public class SysDicConfiguration {

    @Bean
    public DictionaryUtils DictionaryUtils() {
        return new DictionaryUtils();
    }




}
