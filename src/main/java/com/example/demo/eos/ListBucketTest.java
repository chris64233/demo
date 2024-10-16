package com.example.demo.eos;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

/**
 * @author yangchi
 * @date 2024/8/12
 * <p>
 * Description:
 */
public class ListBucketTest {
    public static void main(String[] args) {
        // 填写存储桶（Bucket）所在地域对应的 endpoint 和 Region。
        // 以华东 - 苏州为例，endpoint 填写 https://eos-wuxi-1.cmecloud.cn，Region 填写 wuxi1。
        String endpoint = "https://eos-wuxi-5.cmecloud.cn";
        String region = "wuxi5";

        // 填写 EOS 账号的认证信息，或者子账号的认证信息。
//        String accessKey = "3UKRDRWSD42Z4U2M1H0P";
//        String secretKey = "tvXGOEGSgKanieV3w6c1RnjRbgPY2aDpHG4Jm4MM";
        String accessKey = "XGQBDQUGBL3ZMUNQP9AC";
        String secretKey = "FCm7ndSsGFEx2R9IGGMrA4M26l3DbDHtfNdUfGh7";

        // 创建 AmazonS3 实例。
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider).build();

        // 列举桶。
        List<Bucket> bucketList = client.listBuckets();
        System.out.println(bucketList);

        // 关闭 client。
        client.shutdown();
    }
}
