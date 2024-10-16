package com.example.demo;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Student;
import com.example.demo.test.ApiRequestDto;
import org.slf4j.MDC;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static cn.hutool.core.text.StrPool.COMMA;

public class RabbitMqNettyClientSendMsg {
    @org.junit.Test
    public void test() {
        System.out.println("a");
    }

    @org.junit.Test
    public void test2() {
        BigDecimal a = new BigDecimal("0.00000000");
        BigDecimal b = new BigDecimal("0");
        System.out.println(a.compareTo(b) == 0);
    }

    @org.junit.Test
    public void test3() {
        int page = 0;
        for (int i = 0; i < page; i++) {
            System.out.println();
        }
    }

    @org.junit.Test
    public void test4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.subList(0, Math.min(list.size(), 500)));

    }

    @org.junit.Test
    public void test5() {
        Date date = new Date();
        System.out.println(date);

    }

    @org.junit.Test
    public void test6() {
        Student s = new Student();
        boolean isAdult = s.isAdult();
        System.out.println(isAdult);
    }

    @org.junit.Test
    public void test7() {
        String jsonData = "{\"type\":111,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":{\"ueSn\":\"123456\",\"lon\":\"120.068554\",\"lat\":\"30.286559\",\"boxNum\":10,\"emptyBoxNum\":1,\"disabledBoxNum\":1,\"temp\":40.5,\"dailyActive\":1,\"todayActive\":1,\"dailyExchangeCount\":1.5,\"todayExchangeCount\":1,\"fullSocBatteryCount\":1,\"chargingBatteryCount\":1,\"yesterdayCharging\":1.1,\"electricityMeter\":1.1,\"exStatus\":0,\"boxList\":[{\"boxSn\":1,\"boxEnableStatus\":1,\"disabledReason\":\"no reason\",\"doorStatus\":0,\"cur\":0.00,\"vol\":53.22,\"batterySoc\":100,\"batterySn\":\"123\",\"batteryTemp\":40.5,\"batterySoh\":92}]}}";
        JSONObject ueBizMsgJsonTask = JSONObject.parseObject(jsonData);
        System.out.println(ueBizMsgJsonTask);

    }

    @org.junit.Test
    public void test8() throws Exception {
        ApiRequestDto dto = new ApiRequestDto();
        String appId = "ELE_y9uizr9pka";
        long timestamp = System.currentTimeMillis();
        String biz = "{\"size\":\"50\",\"offset\":\"0\",\"needCabinetAttr\":\"1\"}";
        dto.setBiz(biz);
        dto.setTimestamp(timestamp);
        dto.setAppId(appId);
        String signature = getSignature(dto, "BmN0vAeFHL9rjoz928GbEGx1S9AJP606OzEZwaBwAqQ=");
        System.out.println(signature);

        cn.hutool.json.JSONObject param = new cn.hutool.json.JSONObject();
        param.set("requestId", "1b930f7a5c41459091961cb7c1b624da");
        param.set("version", "1.0.0");
        param.set("sign", signature);
        param.set("timestamp", timestamp);
        param.set("appId", appId);
        param.set("biz", biz);

        String response = HttpUtil.createPost("https://api.xiliulou.com/ele/openapi/cabinet/business/list")
                .body(param.toString())
                .contentType("application/json").execute().body();
        System.out.println(response);
    }

    public String getSignature(ApiRequestDto dto, String appSecret) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("appId", dto.getAppId());
        map.put("timestamp", dto.getTimestamp());
        map.put("biz", dto.getBiz());
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<>(map);
        Set<Map.Entry<String, Object>> entrySet = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder stringToSign = new StringBuilder();
        for (Map.Entry<String, Object> param : entrySet) {
            stringToSign.append(param.getKey()).append("=").append(param.getValue()).append(",");
        }
        stringToSign.deleteCharAt(stringToSign.length() - 1);
        return calSignature(appSecret, stringToSign.toString());
    }

    private String calSignature(String appSecret, String dataToSign) throws Exception {
        String ALGORITHM = "HmacSHA1";
        SecretKeySpec secretKeySpec = new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Mac sha1HMAC = Mac.getInstance(ALGORITHM);
        sha1HMAC.init(secretKeySpec);
        byte[] hmacResult = sha1HMAC.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder encoder = Base64.getUrlEncoder();
        byte[] base64Result = encoder.encode(hmacResult);
        return new String(base64Result);
    }

    @org.junit.Test
    public void test9() {
        System.out.println(System.currentTimeMillis());
    }

    @org.junit.Test
    public void test10() {
        String a = "74.11";
        BigDecimal x = Convert.toBigDecimal(a);
        BigDecimal y = x.multiply(BigDecimal.valueOf(100));
        System.out.println(x);
        System.out.println(y);
        System.out.println(y.longValue());
    }

    @org.junit.Test
    public void test11() {
        System.out.println(Integer.toHexString(1));
    }

    @org.junit.Test
    public void test12() {
        String path = "/home/rkAdmin/nfs-data/images/temp/xxx.jpg";
        File file = FileUtil.touch(path);
        System.out.println(file);
    }

    @org.junit.Test
    public void test13() {
        System.out.println(Convert.toBigDecimal(null));
        System.out.println(Convert.toInt(null));
        System.out.println(Convert.toStr(null, "1"));

        System.out.println(DateUtil.beginOfDay(DateUtil.offsetDay(DateUtil.date(), 2)));
        String[] split = CharSequenceUtil.split(null, COMMA);
        System.out.println(split);
        System.out.println(CollUtil.size(null) < 2);
    }

    @org.junit.Test
    public void test14() {
        Date a = new Date();
        Date b = new Date();
        System.out.println(a.equals(b));
        System.out.println(DateUtil.compare(a, b));
    }

    @org.junit.Test
    public void test15() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("c");
        list2.add("d");
        ArrayList<String> list = new ArrayList<>(CollUtil.union(list1, list2));
        System.out.println(list);

    }
}
