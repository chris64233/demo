package com.example.demo.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.DatadigitalFincloudGeneralsaasFaceCertifyVerifyRequest;
import com.alipay.api.response.DatadigitalFincloudGeneralsaasFaceCertifyVerifyResponse;

/**
 * @author yangchi
 * @date 2024/7/4
 * <p>
 * Description:
 */
public class FaceCertifyVerify {
    public static void main(String[] args) {
        String appId = "2021003189655057";
        String privateKey = "";
        String alipayPublicKey = "";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId, privateKey,"json","GBK", alipayPublicKey,"RSA2");
        DatadigitalFincloudGeneralsaasFaceCertifyVerifyRequest request = new DatadigitalFincloudGeneralsaasFaceCertifyVerifyRequest();
        request.setBizContent("{" +
                "  \"certify_id\":\"cac6d5ac12eee63b9eb02002f8bec790\"" +
                "}");
        DatadigitalFincloudGeneralsaasFaceCertifyVerifyResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            System.out.println(response.getCertifyUrl()); // https://openapi.alipay.com/gateway.do?alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2021003103687478&biz_content=%7B%22certify_id%22%3A%22cac6d5ac12eee63b9eb02002f8bec790%22%7D&charset=UTF-8&format=JSON&method=alipay.user.certify.open.certify&sign=SovRfx16lhmvtbxvDuPbc%2ByqVb1fbUapSFUwfApmLeOU60VFvyEVSCYnYOMqkasFA3Iaiz8PnY4UOAY7HIptUTu3nxO12SVrdVl%2FnCeSbh3vZQQ2eos7HCc1VnOW3TnA%2FJ%2BoIr9U%2B6NnNcaLHemmqgVZBdy%2FWdR0PHKTKl%2BCutdHqBUJCFsiBIU6xe0erOR6oYQ%2BapplK5Bcvf0TPM6Q5q0jDrQ8oWH7LfmovvKWiTbe2X0%2Bda2KS%2FtDRRUFVHvCQkvGMK8%2BIH1hSNeLct60UGdhenUeGTJuDNCsiUEXPS19udd3YT%2FjZZTMYQmX5QM8cLTWb6DYdhQAmzlkq3wmYQ%3D%3D&sign_type=RSA2&timestamp=2024-07-04+16%3A53%3A01&version=1.0&ws_service_url=%24%7Balipay.openapi.ws.url%7D
        } else {
            System.out.println("调用失败");
        }
    }
}

