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

//    public AlipayClient getAlipayClient() {
//
//        String appId = "2021003139690136";
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIbfsze6XcdCVpoQ/nJoigjLHBY2sv6G8UJKLnnQ+JE73qSt1dph10LrbMb1d6U6wg2XaMY3TfuO2s3ZU4zgdWtcikpN7UzZe+pA0exIR7dx0ARlLzfEiMmO+FLtSiWXwVbhYyicio7q5S8MsBZstP14vZGaSOvlMjPNgevPtZkqD77QJNcub7HwFGrS1ewoNJ0/RFWlZfoFf3cVU1b0baXVK+PTA32e+DmNYtpaTfBrHb7QYREzviRW/LUWK1kt2xxM62I9DtIBPB6WvwQMoLjpz4d0sHK0TI4JL9d1fK78GYLLGv4fcY5nCBCHuVvETED3b3ZjIUEp2VLrQ4qDkzAgMBAAECggEAPz2UAvJfGQixyvwA7io7jQiK4VUfe46n5tNwvCJe8DC5iSB46o80+gUpcSxvhWHB2VxUipETvRzw93+jSzidyEDtp/xQ2p4OqC1Nc6SusLivNdBjZUCINLwz0y25HSST0y87lOOvozc4kXPS9mxa9EC7L8CmOhDZYu0yVVVOwd75JvEuhlRWx87OqPZ8o+YQpg+vHNfHy4eg1MiYVpkBvi6G+/jH0YYix1zyWgXaprYNB6HtNF/RMI75bLrDjS0Jr1dTBSnzW9aO1h+lLh7TSM6RV5smKbv2PxI5e3OOD9HMyeSgugg7ItTRKmA6sZQCUagvBO8+Lbh1tX8sfQDTsQKBgQDgGILYZlFyz7PwyEJeNWCBl6yvh+RYM0BCN8vT0Cy9/fR0aRJLtO+J3KyzDstnupw/u4h/nDcmoprLzQqaxgLBYmzoUTjqlxdNoEahyJWXONMAzVPDdexoy05MtSdY672gy497pgriFHDgjnXi+UzdXEKEC2H7fH3fXEebHasn+wKBgQCb2lpeJ1G/TWhDRIX7FnM8Ju4iAiyunvxVQASVuDPk7nDdO6T/8lyFCbaE9xe/1HKBC3Xvv2ulmzb5NVPh5kWEXsXb9Sb2TGC2DBNt231g0fV1kmw/O2iJ8BFL7DtR9Sp0XNygY3GHRuFXj8xE4h6XUKf5eiADNrnAEXDPZwnWKQKBgG+M4/0ymZKsP6c5GIhqy5pW1RgfbhRVlsQfoC/U7aC5vKkB8KCM9bxLhBAx6iC9bK/w+xaCvZVW1fVjIMdAj/iRtSLD47Aq++ehQ+eewJAhMi+xBeRDXCYiJdzbRDXoS2GyUr87PSmjATlW0OnmLVP3bAjs3Z5hLwszlVVqpFtnAoGAIbPKLuoZx2CZ11IOFwn3yV4xyLDnsKs4rca2NxoqPqtBRNYNDDwAvdQn4AD+5cCvMjCkREAEqz8xWBqsFQGSrtk/uBz/W+uQTk22Nd9s8W71wYL0XYgfRxTe6Kgg+8wkbh1WqGhYuIQfXqv95Ddxng8XA9/g5FEFQf+DgZYqjmECgYEAm2C49ZP4x4pwVjtfYzjNiWZVrGOPQzYpKnEiOUiajgte8+I3Z6RckQJeIe5osGjolORpsvv9hRbQRaa6YOml6JwY+VxuyctK9pzCWakW+g9O3VTMeNqUyIAuWCixYZddwhHYgIHOWDB1HWTCjluS8Mii5oxSpioteOOBFGqxCcw=";
//        String serverUrl = "https://openapi.alipay.com/gateway.do";
//        String certPath = "/root/wechatCert/appCertPublicKey_2021003139690136.crt";
//        String alipayPublicCertPath = "/root/wechatCert/alipayCertPublicKey_RSA2.crt";
//        String rootCertPath = "/root/wechatCert/alipayRootCert.crt";
//        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
//        //设置网关地址
//        certAlipayRequest.setServerUrl(serverUrl);
//        //设置应用Id
//        certAlipayRequest.setAppId(appId);
//        //设置应用私钥
//        certAlipayRequest.setPrivateKey(privateKey);
//        //设置请求格式，固定值json
//        certAlipayRequest.setFormat("json");
//        //设置字符集
//        certAlipayRequest.setCharset("utf-8");
//        //设置签名类型
//        certAlipayRequest.setSignType("RSA2");
//        //设置应用公钥证书路径
//        certAlipayRequest.setCertPath(certPath);
//        //设置支付宝公钥证书路径
//        certAlipayRequest.setAlipayPublicCertPath(alipayPublicCertPath);
//        //设置支付宝根证书路径
//        certAlipayRequest.setRootCertPath(rootCertPath);
//
//        try {
//            //构造client
//            return new DefaultAlipayClient(certAlipayRequest);
//        } catch (Exception e) {
//            log.error("初始化AlipayClient失败", e);
//            throw new RuntimeException("初始化AlipayClient失败");
//        }
//    }

    public static AlipayClient getAlipayClient() {
        try {
//            return new DefaultAlipayClient(getAlipayConfig());
            return new DefaultAlipayClient(getAlipayConfigTest());
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static AlipayConfig getAlipayConfig() {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCtaFxUk18zGZW6jASl3xCcVlw5YXdpSLpWKyCCTyPAzHHWDQsSaPrAlswi/X4wRCawC8R/vBJZDjP/NqBlLOxvYkfCse4k8jHUa4aqOXu2zLO8kpiLKrgUpkThm4pv2YBWL4FSUu3PiT3opZSP5O+4L/9aCHDpVqtRJfeoYKbGlUrWO61QaT31OiySvZerIi2pBOj1qysb4rKo9qLW3S7JK/rSPzShKrFIcDbf7wnIrPgPtjqrpOetuk3uXVqK9G/nQxvS5XcOvo8iJrInFtG3ZR9azI8AHRC/+8wDWZ9KxKET1bOf45xIhdQg7XmmBlUNNsMH5C5LjrBt86Eg+qnxAgMBAAECggEAc15lX1eVFNhGx4/0X67izECgpwRzf7aFUmLrBAdHEDR04CZZYWw6t/sb4YOpes88tg9+MkWcJ2fNrFRZrjaPLyPB/oSGnkoMYPkQ5RvNdTcHtcQKfTx0sDhWx4rGNdgNDEoazfyif1sdtzTCfGGiI3a7etj21id1X03VUU7MZn0+n9P3gjM4Yrz8kKjAqlxcBT2uMiObCr7W21M92SrQQDz8ogcHWA/9YW5zrc3nqBmxWZ7e4c+Xp8YLQtx39bl/dgm8VdEhg4zkFRHcBjUMT8hzwwl5w36DcUXaNzDYo/niODgygn8how2dKOtfv8POMTHpnwEJHNhW4G9uFFZAgQKBgQDtvLKCVsFBLjmE2MT2N5igDhtt+tbq/AQIfUlmBMXJN/p+On17OmXACRHTYyY5sqFsTs63q6miybksvijGA6aT2/e2q3G0Jna9JYie9Iy9+W1tmThuUs/Wyi4Js/HNqeF5/bl04wkkDDvQXoxtmXLLxdXb9ynfoVmyPILhTRpg2QKBgQC6upHxh4+ePPLfnIlcqCBWXtC09DgdPDeR9NmbHHcLIaGrkaQN7gw5HZMVMrn2LdPgqAtD0iK9MnVXAs9muzilPoU2ofQ0pFjra+P43sLxge9cG9krWGOyOpuP/7PRXYRdjD//N8/jicJi+gwq8uvgPQ5h+DSVA+9ueJxDqbfi2QKBgCnjFGSomnY8ib/hXZqwIwEYWzbeel1+t8szH+qMrriMV2NENPWwTVsvfeeL0aFa2tw1anCsG0BmYG0EuDRjUkc8+4FA5dDC8irTYSX4mo+iUeh8lZfCmYITr1bgUJtW8jZHQRTaJ7MlQKiu7/kjjzleCxctjkArsp0X0kuWBS7JAoGANXOCo2jyq5E21NuyVluqJg8uHx5pkbTTE2zLfaNAUGdoJ/ygwXwK866Lk93Zbabgp8Zysw9L8mynOikLxZSCOosTBviFNyx0Z6+SqoLZutg+9Fbka4v5veMY9Ld1edETOtQS3bPURoYApuR85y5yZtYTb+fk5WMi/rR3xsamZMECgYEAy43sxT4lqpJShPRfaYYpakqgvdCiyPxnR0YwiKnCK8QNAUAeYxb59gODzudqG+cyJc2SL6081pBcwxYov/iCU6DPWxwaRGK73ffLCiukRXvkSXBolNvzHC6/spyVoGT4XOeowsSOopcF5q39Sdi8Buxz3COwx4ZeruwB1L3gMo0=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwOQ37Xtu8L2Vzr8hagTWpCQtkl85WkQxM/x+M8VoBRxLCbd5in4UHarGc/6NQQBoo9oLokbIVEbkwlTEibiCeLhxIRSWiS1XDZSkDPIsJKb9xYWB4DyGdqSgAH3oH3t/JHb8h4aulPH8jwCDQtDquY3r6gTvSrAQdZEa29hwZJ7iWYa3UCQE5KZpf5sECmuSb5n12eStqZU2w+yp6Qq+qjsowA75HX4V5CeFXDCG2zUJl5WzWzT/laRa20GPyKz+YaJQVx2m5qv7/1PqNkdcUct7FAuanmv6dekq5r73k8jO42TQBYKqoTZnYFnBJgicmVvdlslXRdo5E7OiDQNWyQIDAQAB";
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
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyk0tBk2dlNAJxPbG1PfJvYPpPnPHQesz0/smxfS8OreE8Ci3O6x7wiIN76RxCl48Xs88Mk5sB4IAkQ+Fl+pszcofmnDSAO2XC7ezYSI5awbicsU+WbZWCI3ZJ6rpS6Haa5uRJ9rnn+I4r9HYSzFKO09T29bxElybIZqt8e49GYJRcS/gC4c3uoWkl3C8VqbIFOPS9ZsDFzhj/m96Y9+x4o8tusRKn5LxnVhqdtsJmpKLX2U6GLjNojaaqP7G+DPmbHKN0FPl8t/XD5JeF0PdHfRMzK46N0FRv5kkjeUv9GdyqLTsNYoTKecHNBUS84pPmeb0WK4xOEA+6WZh27EuzAgMBAAECggEALUzCLXtoIjskV3ewCesSAgEW3nVQvnM4ZPnxTk4UTuP7HBD09+WbLqdiu18yJQ5vZTLe1jDnhH1f5FM37Xe6QT3PjceYZeMreFyE1YTqX8l+vHkaWG2qHJ5EVpHt/DQuSjNCPCUTme3qmunjvfcJBmRkRNWnkbrKQtIQtH5fpRlUT2506yUm3aWrN8VViw7q3wVJGuLTf4QagOV2hcSel3Mwn4WM65S1u9ZdRiob2fS7oyiLUMBgSpKjGp+JO6y8CcntSyGdEi/2fH4K79UNZmvmwRT6R2ewWwVmVClO2NpeSsDZxaUYSUDjlofC4kjKVowjbMVZp/7DdecLQeV/cQKBgQDgQD2qZxfUBSpKFIhQNrxwbAIIeL4X6QQbrfE8aK63JA84AAq4YWL7fgo9CMFR4Ts3V875lUFJtEoHcTZA0JFS064rLe94jQfF3ogJ9Gv6mCH8vwqFqcGCRhDTTt7DW0RAS5gHsi/0jq+1dDVVCLxhs6nlloVzWCusf87YEaCLWQKBgQDL25YBNRR1ZUbBuwqVlVSM6dqjRtTBR3nXDcJ6t/2ggLsxt8JbDwghouCk5FETY9l3NGZwNRGLlt6Y76soiT1C3ZM5iiXO+prxM5+64zzW75vtlhY8HaIkXXQPNM27zIQHAI6xwT7SWW28l3aeYjAiSWD1KFPRFyV8RZMv/VBJ6wKBgA8ahIy7kNFI38ZJh7zPUvXHcIOdjkASA6LRnlPRQSM4nIXLCu3MMVx66rmdo1eecdySX3BT6yfJqsrBh0PqSD9p3msuFtjptsJO8J6rBbLFGFTrkQvbmRrrGSsCPuKtfPVPoheymqRAD/mKsCTgB8AU17aM4sQYBikCGq5W0ZdRAoGBAI3SCOx0GfSF7ZCvZ/Cif7y8oB9BEpgX+ppy9x5ay/aSOCihd3PT5EtKPdBZClJb/5kfejiLotYisl9hNQixWU4gSeEYy67Px2UQDgp9NgEtlqPV60gNMZTs8uX1TT9eS2d3qs3c67B5CI/QK3SKSIRB2lMTqCPpX2myGXCkYOvbAoGANfKcWevMIhsxQprbPl5jr3Ewq0qEje0k2PzuRw5RgTC5ABGpPRBcZtPoYnoCzK99VUsDy/VfRaxwwaOtPQvF10QLl8t5y1kVHdw4rD4t1q8Kz0tCLaEECDVhBKATXWag1nIVF9G17P80Cnis9WyYqqoPqB9vngLjsF7ES8HG/mQ=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnRbW7YTSdDu9wfr1gVL7uNDXVv0pusuAbfiad7jq6qnskgOjmAJrxnqfsXWqH3poJ9iUpdUI3jnxdG6NL3oKKXHqCm9GzlwBhjZTr8bJXuyjR8rFkeSiB+ul9z7u76o621TiaAcN00Ho2TdQqsIkhiSYJ2+255Gdnx7qvvkvKV1U6YpOLfwW8+AIDmM9ulKQROAArrxXSSuoa2es+UYHaZ/aR+NDzZtwePRYLu/vfGbVyyzn/xR3GEc2kyjEJ7jc0XxoRhoVg8lzzP4E8SyfIqwka1CbdKbQH9y0YsSQ2IcwYx1FNNyG6eOsxPWfHtUUrIv750X8ReLi78SY2yVxLwIDAQAB";
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
