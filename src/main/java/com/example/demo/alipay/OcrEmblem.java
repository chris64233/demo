package com.example.demo.alipay;

import com.alipay.api.*;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasOcrServerDetectRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasOcrServerDetectResponse;

/**
 * @author yangchi
 * @date 2024/7/4
 * <p>
 * Description:
 */
public class OcrEmblem {
    public static void main(String[] args) {
        String appId = "2021004155604130";
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCnHE3i+zZMC2fshkS8Rijyoxy8DggbhpQC1rvkInp49BCxdjwXB8uLBJEHmB6I6LcjshZPKT8g2rOxT8W8PZdOU/2lchdSIlNWvDtD9nOdn+r3FjfeiETTm8POZfpkCKDXRNN1jc/Mcmr1SNfaePXxaPv6fw9BeLHxuTTbcf6hc7Bvh33f3YfWY1P3Ytn1VHOxrCmsYyPUwvkKL8zixIDKUvmXekXagXNK0oIKtfVLh7Y6nQOjuJnihV7uWk9B0yJ+zT2k743Qd1W44RlRCcuF1jexiq6+S2MdsHM6TwpFTa8KuRU/AJYK3CkmC0lK78stKFeZQ3Hswj7IZtB1GXoDAgMBAAECggEAJB0ey+XENyzj2Z6urg1vffg4Fn/lyqULyY1Eb6nv08HS9bb6h3Oczj8/ZGfAeY/pPCKZZaQhS2VYHgKCVgrtbvUBxMZiOdLHNeBZ4ncZR0AvFBTOIB5M2Xo5F5q8QPAIal0qYaYy+Rjk+nsygrlPVUt94QWic2C0++stfBsnjxZJM9FKXlkFNqomTisyRk/TgJMpbZJYYJJ7t7qi8OXP397Y+gcLUE/l36iRqPvt9U8zo6/sJVyrzvKy7WudKiSGSDMbOyHduYuzjmn05UnYxO+bnLDs714P0yeL7et5uGWciY1a9MwyBfFluldh8mZwcxuNNpZEON8cVp2amFYBEQKBgQD7r6LabK41XtRTnn5DYcIUalXrqHJv92spId4NF54Y43osEV7xNbVzdC1HDxz7gmtktjP8FILhY4I+JxEnW9MqynPp4c/hVh83edNhjWTqZKtfpeZ8B1dHj5hHIzwk9sS5L+r/7GMyc1i0adQhVD9RUi1D2i2v4fbtAvjJAURtuQKBgQCp+Y/4dua9Y07HzVMg5bQ2Ypm3mC36ilgKuHe7Nfq0OKR6Sh1UsQeuN/Rk8Y4dEG7HWhX+qUcWgzJOhlmA3opYGtjEo087e2KHFK0HEvaGO6QL+GZMDQcsbvZMM5I5cw8iXwJrifoCixHzA4U3zS57PL/3GDk5oCmGH0PRzmDjmwKBgD2rKWIPoxfcpnXerd1gxKcv7uGRIokgvJcVf00B7nD2u+a0UukTWeRyIO36XAWcTSgeJNqtQ+xp722/MMrOCw2/EWvLMp5p9bgoF40dkg6EoCENi030loQoQQ2dKo18EXO2tAQFLcEc1i1OiPWpqOIKGqE3gl5IJbKpOcA2mUexAoGBAJuhO2c+RwrFMM7Ueva2G4loQmLN3dt2bCJ0wHwTcpcs/bxSvARdizDDXuxGF98YurWhEF550/MS8S7g6HaMTJNbEXsKJHPMB2yzS7IcdMh3Nz8Wk2Oy0gynFLC0z3/2j7bi0y5vyRAnEao2YPW00ohYGVQ/Q7Y1pY9+dPRXSHNdAoGBAL4kJ+ybvdMso13J3eFyt3qCy48PFKpg+3ntEqRseVZg9CZ1W/9QweLzGbtsP3iYWN6GrR+hBUc2kmUF4RhtzC/5YoJgNbluWeFExsWCGkVTdbupV+QhoSqZDw9V37dlp1K5V6AHn9F96OZvl3SWhgYkGN6GSFs/8gGcUR7lZzy+";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwOQ37Xtu8L2Vzr8hagTWpCQtkl85WkQxM/x+M8VoBRxLCbd5in4UHarGc/6NQQBoo9oLokbIVEbkwlTEibiCeLhxIRSWiS1XDZSkDPIsJKb9xYWB4DyGdqSgAH3oH3t/JHb8h4aulPH8jwCDQtDquY3r6gTvSrAQdZEa29hwZJ7iWYa3UCQE5KZpf5sECmuSb5n12eStqZU2w+yp6Qq+qjsowA75HX4V5CeFXDCG2zUJl5WzWzT/laRa20GPyKz+YaJQVx2m5qv7/1PqNkdcUct7FAuanmv6dekq5r73k8jO42TQBYKqoTZnYFnBJgicmVvdlslXRdo5E7OiDQNWyQIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,"json","GBK",alipayPublicKey,"RSA2");
        DatadigitalFincloudGeneralsaasOcrServerDetectRequest request = new DatadigitalFincloudGeneralsaasOcrServerDetectRequest();
        request.setOcrType("ID_CARD_BACK");
        request.setOuterOrderNo("ccc_test_ocr_001");
        FileItem FileContent = new FileItem("C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\国徽面.jpg");
        request.setFileContent(FileContent);
        DatadigitalFincloudGeneralsaasOcrServerDetectResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
