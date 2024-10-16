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
        String appId = "2021003189655057";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyk0tBk2dlNAJxPbG1PfJvYPpPnPHQesz0/smxfS8OreE8Ci3O6x7wiIN76RxCl48Xs88Mk5sB4IAkQ+Fl+pszcofmnDSAO2XC7ezYSI5awbicsU+WbZWCI3ZJ6rpS6Haa5uRJ9rnn+I4r9HYSzFKO09T29bxElybIZqt8e49GYJRcS/gC4c3uoWkl3C8VqbIFOPS9ZsDFzhj/m96Y9+x4o8tusRKn5LxnVhqdtsJmpKLX2U6GLjNojaaqP7G+DPmbHKN0FPl8t/XD5JeF0PdHfRMzK46N0FRv5kkjeUv9GdyqLTsNYoTKecHNBUS84pPmeb0WK4xOEA+6WZh27EuzAgMBAAECggEALUzCLXtoIjskV3ewCesSAgEW3nVQvnM4ZPnxTk4UTuP7HBD09+WbLqdiu18yJQ5vZTLe1jDnhH1f5FM37Xe6QT3PjceYZeMreFyE1YTqX8l+vHkaWG2qHJ5EVpHt/DQuSjNCPCUTme3qmunjvfcJBmRkRNWnkbrKQtIQtH5fpRlUT2506yUm3aWrN8VViw7q3wVJGuLTf4QagOV2hcSel3Mwn4WM65S1u9ZdRiob2fS7oyiLUMBgSpKjGp+JO6y8CcntSyGdEi/2fH4K79UNZmvmwRT6R2ewWwVmVClO2NpeSsDZxaUYSUDjlofC4kjKVowjbMVZp/7DdecLQeV/cQKBgQDgQD2qZxfUBSpKFIhQNrxwbAIIeL4X6QQbrfE8aK63JA84AAq4YWL7fgo9CMFR4Ts3V875lUFJtEoHcTZA0JFS064rLe94jQfF3ogJ9Gv6mCH8vwqFqcGCRhDTTt7DW0RAS5gHsi/0jq+1dDVVCLxhs6nlloVzWCusf87YEaCLWQKBgQDL25YBNRR1ZUbBuwqVlVSM6dqjRtTBR3nXDcJ6t/2ggLsxt8JbDwghouCk5FETY9l3NGZwNRGLlt6Y76soiT1C3ZM5iiXO+prxM5+64zzW75vtlhY8HaIkXXQPNM27zIQHAI6xwT7SWW28l3aeYjAiSWD1KFPRFyV8RZMv/VBJ6wKBgA8ahIy7kNFI38ZJh7zPUvXHcIOdjkASA6LRnlPRQSM4nIXLCu3MMVx66rmdo1eecdySX3BT6yfJqsrBh0PqSD9p3msuFtjptsJO8J6rBbLFGFTrkQvbmRrrGSsCPuKtfPVPoheymqRAD/mKsCTgB8AU17aM4sQYBikCGq5W0ZdRAoGBAI3SCOx0GfSF7ZCvZ/Cif7y8oB9BEpgX+ppy9x5ay/aSOCihd3PT5EtKPdBZClJb/5kfejiLotYisl9hNQixWU4gSeEYy67Px2UQDgp9NgEtlqPV60gNMZTs8uX1TT9eS2d3qs3c67B5CI/QK3SKSIRB2lMTqCPpX2myGXCkYOvbAoGANfKcWevMIhsxQprbPl5jr3Ewq0qEje0k2PzuRw5RgTC5ABGpPRBcZtPoYnoCzK99VUsDy/VfRaxwwaOtPQvF10QLl8t5y1kVHdw4rD4t1q8Kz0tCLaEECDVhBKATXWag1nIVF9G17P80Cnis9WyYqqoPqB9vngLjsF7ES8HG/mQ=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnRbW7YTSdDu9wfr1gVL7uNDXVv0pusuAbfiad7jq6qnskgOjmAJrxnqfsXWqH3poJ9iUpdUI3jnxdG6NL3oKKXHqCm9GzlwBhjZTr8bJXuyjR8rFkeSiB+ul9z7u76o621TiaAcN00Ho2TdQqsIkhiSYJ2+255Gdnx7qvvkvKV1U6YpOLfwW8+AIDmM9ulKQROAArrxXSSuoa2es+UYHaZ/aR+NDzZtwePRYLu/vfGbVyyzn/xR3GEc2kyjEJ7jc0XxoRhoVg8lzzP4E8SyfIqwka1CbdKbQH9y0YsSQ2IcwYx1FNNyG6eOsxPWfHtUUrIv750X8ReLi78SY2yVxLwIDAQAB";
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
