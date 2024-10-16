package com.example.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.OpenCertifyMerchantConfigs;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceCertifyInitializeResponse;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceCertifyInitializeRequest;
import com.alipay.api.domain.OpenCertifyIdentifyInfo;
import com.alipay.api.domain.DatadigitalFincloudGeneralsaasFaceCertifyInitializeModel;

import com.example.demo.util.AlipayUtil;

import java.util.UUID;

public class FaceCertifyInitialize {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = AlipayUtil.getAlipayClient();

        // 构造请求参数以调用接口
        DatadigitalFincloudGeneralsaasFaceCertifyInitializeRequest request = new DatadigitalFincloudGeneralsaasFaceCertifyInitializeRequest();
        DatadigitalFincloudGeneralsaasFaceCertifyInitializeModel model = new DatadigitalFincloudGeneralsaasFaceCertifyInitializeModel();

        // 设置H5人脸核身场景码
        model.setBizCode("FUTURE_TECH_BIZ_FACE_SDK");

        // 设置认证信息
        OpenCertifyIdentifyInfo identityParam = new OpenCertifyIdentifyInfo();
//        identityParam.setPhoneNo("15867704132");
        identityParam.setCertType("IDENTITY_CARD");
        identityParam.setCertName("杨驰");
        identityParam.setCertNo("330327199103060238");
        identityParam.setIdentityType("CERT_INFO");
        model.setIdentityParam(identityParam);

        // 设置商户请求的唯一标识
        model.setOuterOrderNo(UUID.randomUUID().toString().replace("-", ""));

        // 设置商户配置信息
        OpenCertifyMerchantConfigs merchantConfig = new OpenCertifyMerchantConfigs();
        merchantConfig.setFaceReserveStrategy("reserve");
//        merchantConfig.setReturnUrl("https://xxx");
        model.setMerchantConfig(merchantConfig);

        request.setBizModel(model);
        DatadigitalFincloudGeneralsaasFaceCertifyInitializeResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println(response.getCertifyId()); // cac6d5ac12eee63b9eb02002f8bec790
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }

}