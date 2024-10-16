package com.example.demo.obs.test;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.example.demo.obs.constant.Constants.*;

/**
 * @author yangchi
 * @date 2024/8/13
 * <p>
 * Description:
 */
public class UploadTest {
    public static void main(String[] args) {
        String endPoint = ENDPOINT;
        String ak = AK;
        String sk = SK;
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);


        // 指定本地图片文件的路径
        String imagePath = "C:\\Users\\Administrator\\Desktop\\浙商中拓\\需求\\换电\\资料\\图片\\test.jpg";

        // 创建FileInputStream来读取图片文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imagePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        PutObjectResult result = obsClient.putObject(BUCKET_NAME, "test", inputStream);
    }
}
