package com.example.demo.alipay;

import com.alipay.api.*;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceSourceCertifyRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceSourceCertifyResponse;
import com.example.demo.util.AlipayUtil;
import com.example.demo.util.Base64Util;
import com.example.demo.util.FileUtil;

import java.io.IOException;
import java.util.Base64;

public class FaceSourceCertify {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
//        AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());
        AlipayClient alipayClient = AlipayUtil.getAlipayClient();

        byte[] imgData;
        String filePath = "C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\cccc.jpg";
        try {
            imgData = FileUtil.readFileByBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String imgStr = Base64Util.encode(imgData);

        //这是一个1x1像素的图片流
//        String imageBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAEUlEQVR42mP4TyRgGFVIX4UAI/uOgGWVNeQAAAAASUVORK5CYII=";
        String imageBase64 = imgStr;
        // 下列FileItem中也可用直接读取本地文件的方式来获取文件
         FileItem imageContent = new FileItem(filePath);

        // 构造请求参数以调用接口
        DatadigitalFincloudGeneralsaasFaceSourceCertifyRequest request = new DatadigitalFincloudGeneralsaasFaceSourceCertifyRequest();

        // 设置二进制流图片
        FileItem fileContent = new FileItem("fileContent.jpg", Base64.getDecoder().decode(imageBase64));
//        request.setFileContent(fileContent);
        request.setFileContent(imageContent);

        // 设置证件信息类型
        request.setCertType("IDENTITY_CARD");

        // 设置用户姓名
        request.setCertName("杨驰");

        // 设置证件号码
        request.setCertNo("330327199103060238");

        // 设置客户业务单据号
        request.setOuterBizNo("chris_biz_test_001");

        // 设置安全策略
        request.setSecurityStrategy("ON");

        // 设置活体检测类型
        request.setLivenessStrategy("CHECK");

        request.setNeedEncrypt(false);

        DatadigitalFincloudGeneralsaasFaceSourceCertifyResponse response = alipayClient.execute(request);
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
}