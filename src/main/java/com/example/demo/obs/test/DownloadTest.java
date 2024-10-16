package com.example.demo.obs.test;

import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import java.io.*;

import static com.example.demo.obs.constant.Constants.*;

/**
 * @author yangchi
 * @date 2024/8/13
 * <p>
 * Description:
 */
public class DownloadTest {
    public static void main(String[] args) throws IOException {
        String endPoint = ENDPOINT;
        String ak = AK;
        String sk = SK;
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);


        ObsObject obsObject = obsClient.getObject(BUCKET_NAME, "test");
        // 读取对象内容
        System.out.println("Object content:");
        InputStream input = obsObject.getObjectContent();
        byte[] b = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        while ((len=input.read(b)) != -1){
            bos.write(b, 0, len);
        }

        System.out.println(new String(bos.toByteArray()));
        bos.close();
        input.close();
    }
}
