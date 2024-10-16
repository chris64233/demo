package com.example.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.domain.DatadigitalFincloudGeneralsaasFaceVerificationQueryModel;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceVerificationQueryRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceVerificationQueryResponse;
import com.example.demo.util.AlipayUtil;

public class FaceVerificationQuery {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = AlipayUtil.getAlipayClient();

        // 构造请求参数以调用接口
        DatadigitalFincloudGeneralsaasFaceVerificationQueryRequest request = new DatadigitalFincloudGeneralsaasFaceVerificationQueryRequest();
        DatadigitalFincloudGeneralsaasFaceVerificationQueryModel model = new DatadigitalFincloudGeneralsaasFaceVerificationQueryModel();

        // 设置填入人脸核身初始化阶段获取到的certify_id
        model.setCertifyId("dtfv_27f8afc826c11bfeb21dd92917ea55f2");

        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        DatadigitalFincloudGeneralsaasFaceVerificationQueryResponse response = alipayClient.execute(request);
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