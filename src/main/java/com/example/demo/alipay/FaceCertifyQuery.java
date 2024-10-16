package com.example.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceCertifyQueryRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceCertifyQueryResponse;
import com.alipay.api.domain.DatadigitalFincloudGeneralsaasFaceCertifyQueryModel;
import com.example.demo.util.AlipayUtil;

public class FaceCertifyQuery {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = AlipayUtil.getAlipayClient();

        // 构造请求参数以调用接口
        DatadigitalFincloudGeneralsaasFaceCertifyQueryRequest request = new DatadigitalFincloudGeneralsaasFaceCertifyQueryRequest();
        DatadigitalFincloudGeneralsaasFaceCertifyQueryModel model = new DatadigitalFincloudGeneralsaasFaceCertifyQueryModel();

        // 设置本次申请操作的唯一标识
        model.setCertifyId("dcc3202c98ee9cd679211150c8bf1c6c");

        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        DatadigitalFincloudGeneralsaasFaceCertifyQueryResponse response = alipayClient.execute(request);
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
        String privateKey  = "<-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->";
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