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
        String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDF3YJ16icCvEs0oWme2fi0s+Tq+yzzNiCoWn349muQWmgd/+XdaxzRdwafDCgK1j7N0lHa6KUbEyfB6NOWmEwR+6P7W++WOBYMOW1Ts5RRDs61XJMShNMeZoot08rijnLKCQ/YnfeucjVCrWhh/IPCoeF0KKF/ztN+QXfnSm62CzlW8EGgrvUd0o4Op+FiJ6nhAkcgUZ20ThCtUK2yIAgsOtXttcHemFmzHU66yVgruB1FJlN+PSeev2oL1oMJzQd4TQzRbM0iKNiDUrUweyrKugAVNI9SMEeQ3jc6mBobIoDFFunhGNouV4WlKXH9CqPDnV+gN+3GSlL7TjVFFdxpAgMBAAECggEBALQvaRu6NPZPxGjUSRvowYzFecTnIWMGOq01YxxyQzf8rG4NWCXP4jl6G+SArIJZ4m2KhxJD4/8I7Z6vSOgOHtANiaJgcYkp4CIXtdCQa5N5WZJdEG2/2YYIuPLMuSihsjT8FZsdF62bwZbL1Y/N5as7wdUHHzMFhSGnuuo8pR9O9N+xxBDcRdLcqVUeVrTW5K5d4voh4vNY3072UWiKz9l3B4HKOoukcepu4pJ9o1o4qcgd5ABt//tJozZZp9xRiZCcwyJyMWU/QIvKMcQt0FpSaM17Pz830es1VXb/5EGxVGk4ZlbVPPs01M911SwbXCI4Oibkf5zhlj1sdPbYzFECgYEA7Ot0HoghXlQHWeLyORvRTM9GcSjOxGLI92vlh4bcgVbuzb/NWzNbQJaAWNnMwZIVBFQhjtbFEHuWUE5wiF96gwwotYrhG8kgTZ7RxSyTTwm4pre8KSMZS3FTl+SY5DIMKmhJQnlMbPgreLKSQB0/XlPaj9V2iJk2WmECk6dQKTUCgYEA1czf6C5+BhzDUIrRM2YTrnTkGpkrR2pSiSxBgnUlC0T7heD5FNSi61BH2l+Gc91WeGRKgGp21yMeiagRP5E1xiY9Q4sIxkLo539x3twsks+U+IJhiZU9LpjpOU0CShGJ68ivM1geOBSOaHmiID+/S0Ny/dj5/za0/1QcxX39AOUCgYEAjbeh9Al1/FjDNO9d+O882Xc7KcFBPZPhpq6x08nF6NA5dHWAv0cPFKlyCLPvmPJrA/XXRPwPUPa8STlCczC5td32E/IJCXfbjNKtpAYv751fCMrrEKhgHxKg8EDbe9S8FjnfaJXqM1yXlFEszpEwMJp0KImFp9wN+S4CPpRcjOkCgYBg9VWBvTvn17l32EyBa8R3NImRym7HJ1+g19IpsKiJENKkZ19uY/QylSjQzstx1in11T24ORAGBuY9dVvFITxqzUKxU+c5ynRHMBPbBqysM7mSbcaAF480h6IVG2hzYLrUlCFoPokN07y1gpvU1jQRlNgcaP8PD6dLt8CIi9dmRQKBgQDe85wx7Rc2ieLMcXqpKRssaybNOmPVpxNykdtfnLemug0KxpausLjVF0YUPFdnOZlDMsZBngg9pZij+qITchpDnKjWJjW+/dU+dT1AIu4iP0Z8qSr0biHybkRH5pRQqtCn1cZqz7ow017U3YqgN2/juUyRDKq5fyf9SeYNuu5h+w==";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnRbW7YTSdDu9wfr1gVL7uNDXVv0pusuAbfiad7jq6qnskgOjmAJrxnqfsXWqH3poJ9iUpdUI3jnxdG6NL3oKKXHqCm9GzlwBhjZTr8bJXuyjR8rFkeSiB+ul9z7u76o621TiaAcN00Ho2TdQqsIkhiSYJ2+255Gdnx7qvvkvKV1U6YpOLfwW8+AIDmM9ulKQROAArrxXSSuoa2es+UYHaZ/aR+NDzZtwePRYLu/vfGbVyyzn/xR3GEc2kyjEJ7jc0XxoRhoVg8lzzP4E8SyfIqwka1CbdKbQH9y0YsSQ2IcwYx1FNNyG6eOsxPWfHtUUrIv750X8ReLi78SY2yVxLwIDAQAB";
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

