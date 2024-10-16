package com.example.demo.alipay;

import cn.hutool.http.HttpUtil;
import com.alipay.api.*;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasOcrServerDetectRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasOcrServerDetectResponse;

import java.io.UnsupportedEncodingException;

/**
 * @author yangchi
 * @date 2024/7/4
 * <p>
 * Description:
 */
public class OcrFace {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String appId = "2021003189655057";
        String privateKey = "";
        String alipayPublicKey = "";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,"json","GBK",alipayPublicKey,"RSA2");
        DatadigitalFincloudGeneralsaasOcrServerDetectRequest request = new DatadigitalFincloudGeneralsaasOcrServerDetectRequest();
        request.setOcrType("ID_CARD_FRONT");
        request.setOuterOrderNo("ccc_test_ocr_001");
        FileItem FileContent = new FileItem("C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\头像面.jpg");
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
