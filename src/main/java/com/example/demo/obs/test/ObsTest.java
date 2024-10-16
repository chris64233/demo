package com.example.demo.obs.test;

import com.example.demo.obs.constant.Constants;
import com.obs.services.ObsClient;
import com.obs.services.model.ListBucketsRequest;
import com.obs.services.model.ObsBucket;

import java.io.IOException;
import java.util.List;

import static com.example.demo.obs.constant.Constants.*;

/**
 * @author yangchi
 * @date 2024/8/13
 * <p>
 * Description:
 */
public class ObsTest {
    public static void main(String[] args) throws IOException {
        String endPoint = ENDPOINT;
        String ak = AK;
        String sk = SK;
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        boolean exists = obsClient.headBucket(BUCKET_NAME);


        // 列举桶
        ListBucketsRequest request = new ListBucketsRequest();
        request.setQueryLocation(true);
        List<ObsBucket> buckets = obsClient.listBuckets(request);
        for(ObsBucket bucket : buckets){
            System.out.println("BucketName:" + bucket.getBucketName());
            System.out.println("CreationDate:" + bucket.getCreationDate());
            System.out.println("Location:" + bucket.getLocation());
        }


        // 关闭obsClient，全局使用一个ObsClient客户端的情况下，不建议主动关闭ObsClient客户端
        obsClient.close();

    }
}
