package com.example.demo.obs.test;

import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.demo.obs.constant.Constants.*;

/**
 * @author yangchi
 * @date 2024/8/13
 * <p>
 * Description:
 */
public class DeleteTest {
    public static void main(String[] args) throws IOException {
        String endPoint = ENDPOINT;
        String ak = AK;
        String sk = SK;


        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.deleteObject(BUCKET_NAME, "test");
    }
}
