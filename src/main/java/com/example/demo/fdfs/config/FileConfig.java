package com.example.demo.fdfs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@Data
public class FileConfig {

    @Value("${fdfs.baseUrl}")
    private String baseUrl;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大  5MB
        factory.setMaxFileSize(DataSize.ofBytes(5 * 1024 * 1024));
        /// 设置总上传数据总大小 20MB
        factory.setMaxRequestSize(DataSize.ofBytes(20 * 1024 * 1024));
        return factory.createMultipartConfig();
    }
}
