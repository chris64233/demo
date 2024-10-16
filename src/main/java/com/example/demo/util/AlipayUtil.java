package com.example.demo.util;

import com.alipay.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created in 2022/8/4 10:14
 *
 * @author ：LiLei
 */
@Component
@Slf4j
public class AlipayUtil {


    public static AlipayClient getAlipayClient() {
        try {
//            return new DefaultAlipayClient(getAlipayConfig());
            return new DefaultAlipayClient(getAlipayConfigTest());
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static AlipayConfig getAlipayConfig() {
        String privateKey = "";
        String alipayPublicKey = "";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId("2021004155604130");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }

    /**
     * 测试用
     * @return
     */
    private static AlipayConfig getAlipayConfigTest() {
        String privateKey = "";
        String alipayPublicKey = "";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId("2021003189655057");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        alipayConfig.setEncryptKey("2O/eG33SMVA79gLeO+YCFQ==");
        return alipayConfig;
    }
}
