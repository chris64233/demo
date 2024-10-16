package com.example.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.domain.DatadigitalFincloudGeneralsaasFaceVerificationInitializeModel;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceVerificationInitializeRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceVerificationInitializeResponse;
import com.example.demo.util.AlipayUtil;

import java.util.UUID;

public class FaceVerificationInitialize {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = AlipayUtil.getAlipayClient();

        // 构造请求参数以调用接口
        DatadigitalFincloudGeneralsaasFaceVerificationInitializeRequest request = new DatadigitalFincloudGeneralsaasFaceVerificationInitializeRequest();
        DatadigitalFincloudGeneralsaasFaceVerificationInitializeModel model = new DatadigitalFincloudGeneralsaasFaceVerificationInitializeModel();

        // 设置证件类型
        model.setCertType("IDENTITY_CARD");

        // 设置真实姓名
        model.setCertName("杨驰");

        // 设置人脸核身具体类型目前仅支持
        model.setBizCode("DATA_DIGITAL_BIZ_CODE_FACE_VERIFICATION");

        // 设置证件号
        model.setCertNo("330327199103060238");

        // 设置商户请求的唯一标识
        model.setOuterOrderNo(UUID.randomUUID().toString().replace("-", ""));

        // 设置认证类型
        model.setIdentityType("CERT_INFO");

        request.setBizModel(model);
        request.setNeedEncrypt(true);
        DatadigitalFincloudGeneralsaasFaceVerificationInitializeResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }

    private static AlipayConfig getAlipayConfig() {
        String privateKey = "<-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->";
        String alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId("<-- 请填写您的AppId，例如：2019091767145019 -->");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }
}