package com.example.demo.eos;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @author yangchi
 * @date 2024/8/12
 * <p>
 * Description:
 */
public class CreateBucketTest {
    public static void main(String[] args) {
        // 填写存储桶（Bucket）所在地域对应的 endpoint 和 Region。
        // 以华东 - 苏州为例，endpoint 填写 https://eos-wuxi-1.cmecloud.cn，Region 填写 wuxi1。
        String endpoint = "https://eos-wuxi-1.cmecloud.cn";
        String region = "wuxi1";

        // 填写 EOS 账号的认证信息，或者子账号的认证信息。
        String accessKey = "7624df1eb2b4405d9b6d88a72dfb9697";
        String secretKey = "6dcf2d9f861d48ec8066edf0fde527d2";

        // 填写要创建的存储桶名称，例如'example-bucket'。
        String bucketName = "test";

        // 创建 AmazonS3 实例。
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider).build();

        // 创建存储桶。
        try {
            // client.createBucket 方法返回 Bucket 实例，包含创建的存储桶的信息。
            Bucket bucket = client.createBucket(bucketName);
            if (bucket != null) {
                System.out.println("Name: " + bucket.getName());
            }
        } catch (AmazonServiceException e) {
            // 创建失败。
            System.out.println("发生异常 AmazonServiceException，通常原因是请求内容错误。");
            System.out.println("Error Code: " + e.getErrorCode());
        } catch (SdkClientException e) {
            // 创建失败。
            System.out.println("发生异常 SdkClientException，通常原因是未能连接到 EOS 服务。");
            System.out.println("Error Message: " + e.getMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }
}
