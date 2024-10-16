package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AdapayConfig {

    /**
     * 湖南凌寒
     */
    @Value("${adapay.linghan.appId}")
    private String appIdLh;

    @Value("${adapay.linghan.apiKey}")
    private String apiKeyLh;

    @Value("${adapay.linghan.mockApiKey}")
    private String mockApiKeyLh;

    @Value("${adapay.linghan.rsaPrivateKey}")
    private String rsaPrivateKeyLh;

    @Value("${adapay.linghan.zipFileName}")
    private String zipFileNameLh;

}
