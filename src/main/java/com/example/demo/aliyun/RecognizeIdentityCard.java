package com.example.demo.aliyun;/*
引入依赖包
<!-- https://mvnrepository.com/artifact/com.aliyun/ocr20191230 -->
<dependency>
      <groupId>com.aliyun</groupId>
      <artifactId>ocr20191230</artifactId>
      <version>${aliyun.ocr.version}</version>
</dependency>
*/

import com.aliyun.ocr20191230.models.RecognizeIdentityCardResponse;
import com.aliyun.tea.TeaModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class RecognizeIdentityCard {
    public static com.aliyun.ocr20191230.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        /*
          初始化配置对象com.aliyun.teaopenapi.models.Config
          Config对象存放 AccessKeyId、AccessKeySecret、endpoint等配置
         */
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "ocr.cn-shanghai.aliyuncs.com";
        return new com.aliyun.ocr20191230.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        // "YOUR_ACCESS_KEY_ID", "YOUR_ACCESS_KEY_SECRET" 的生成请参考https://help.aliyun.com/document_detail/175144.html
        // 如果您是用的子账号AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参考https://help.aliyun.com/document_detail/145025.html
        com.aliyun.ocr20191230.Client client = RecognizeIdentityCard.createClient("", "");
        // 场景一，使用本地文件
         InputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\国徽面.jpg"));
        // 场景二，使用任意可访问的url
//        URL url = new URL("https://viapi-test-bj.oss-cn-beijing.aliyuncs.com/viapi-3.0domepic/ocr/RecognizeIdentityCard/sfzbm1.jpg");
//        InputStream inputStream = url.openConnection().getInputStream();
        com.aliyun.ocr20191230.models.RecognizeIdentityCardAdvanceRequest recognizeIdentityCardAdvanceRequest = new com.aliyun.ocr20191230.models.RecognizeIdentityCardAdvanceRequest()
                .setImageURLObject(inputStream)
                .setSide("face");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            RecognizeIdentityCardResponse recognizeIdentityCardResponse = client.recognizeIdentityCardAdvance(recognizeIdentityCardAdvanceRequest, runtime);
            // 获取整体结果。
            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(recognizeIdentityCardResponse)));
            // 获取单个字段。
            System.out.println(recognizeIdentityCardResponse.getBody());
        } catch (com.aliyun.tea.TeaException teaException) {
            // 获取整体报错信息。
            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段。
            System.out.println(teaException.getCode());
        }
    }
}