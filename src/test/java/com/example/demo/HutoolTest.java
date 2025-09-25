package com.example.demo;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.BatteryInfoLog;
import com.example.demo.domain.DoorInfoLog;
import com.example.demo.domain.Student;
import com.example.demo.hutool.Iso8601Util;
import com.example.demo.hutool.entity.MessageContent;
import com.example.demo.test.ApiRequestDto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import static cn.hutool.core.text.StrPool.*;

public class HutoolTest {
    @org.junit.Test
    public void test() {
        String swiftMessage = "{1:F01senderBank111}{2:}{3:{108:}}{4:\n" +
                ":15A:\n" +
                ":20:referenceNumber111\n" +
                ":22A:NEWT\n" +
                ":22C:commonReference111\n" +
                ":82A:senderBank111\n" +
                ":87A:receiverBank111\n" +
                ":16B:\n" +
                ":30T:20250320\n" +
                ":30V:20250320\n" +
                ":36:7.134\n" +
                ":32B:CCY100\n" +
                ":56a:intermediaryBank111\n" +
                ":57A:receiverProxyInfo111\n" +
                ":33B:USD110\n" +
                ":57A:receiverProxyInfo111\n" +
                "-}";

        // 使用 Hutool 提取 :20: 后的值
        String value = StrUtil.subBetween(swiftMessage, ":20:", "\n");

        System.out.println("提取的值: " + value);
    }

    @org.junit.Test
    public void test2() {
        // 示例 List
        List<String> list = Arrays.asList("Apple", "Banana", "Orange", "Grape");

        // 目标字符串，忽略大小写
        String target = "apple";

        // 使用 Hutool CollUtil 判断
        boolean contains = CollUtil.contains(list, item -> StrUtil.equalsIgnoreCase(item, target));

        // 输出结果
        System.out.println("List 是否包含目标字符串: " + contains);
    }

    @org.junit.Test
    public void test3() {
        String path = "C:\\Users\\Administrator/jupiter/upload/temp/\\20250325\\1354103380886994944.xml";
        System.out.println(FileUtil.readUtf8String(path));
    }

    @org.junit.Test
    public void test4() {
        String yyMMdd = DateUtil.format(DateUtil.parse("20250328", "yyyyMMdd"), "yyMMdd");
        System.out.println(yyMMdd);
    }

    @org.junit.Test
    public void test5() {
        BigDecimal b = BigDecimal.valueOf(1000.11);
        String replace = NumberUtil.toStr(b).replace(DOT, COMMA);
        System.out.println(replace);
    }

    @org.junit.Test
    public void test6() {

        String a = "dbcaefghddhh";
        String b = "zxbyacdeXXX";

        // 1. 逐字符比较 a 和 b，确定顺序
        String first = StrUtil.compare(a, b, false) <= 0 ? a : b;
        String second = first.equals(a) ? b : a;

        // 2. 截取前 4位 + 后 2位（若后 3位为 XXX，则忽略 XXX取第 7、8位）
        String extractedFirst = extractParts(first);
        String extractedSecond = extractParts(second);


        // 3. 拼接结果
        System.out.println(extractedFirst + extractedSecond);
    }

    private static String extractParts(String str) {
        int len = str.length();
        String firstFour = StrUtil.sub(str, 0, 4);
        String lastTwo = "";

        String lastThree = StrUtil.sub(str, len - 3, len);
        if ("XXX".equals(lastThree)) {
            lastTwo = StrUtil.sub(str, 6, 8); // 取第7和第8位（索引6和7）
        } else {
            lastTwo = StrUtil.sub(str, len - 2, len); // 取最后两位
        }

        return firstFour + lastTwo;
    }

    @org.junit.Test
    public void test7() {
        System.out.println(extractNumbers("0,0,1,0,5,0,0"));  // 0105
        System.out.println(extractNumbers("0,0,0,0,3,2,1"));  // 0321
        System.out.println(extractNumbers("0,0,0,0,0,0,9"));  // 0009
        System.out.println(extractNumbers("0,0,0,0,0,0,0"));  // 0000
        System.out.println(extractNumbers("1,2,3"));         // 0123
        System.out.println(extractNumbers("1,1800"));  // 0118
    }

    private static String extractNumbers(String input) {
        String numbers = StrUtil.removeAll(input, ",");

        int lastIndex = -1;
        for (int i = numbers.length() - 1; i >= 0; i--) {
            if (numbers.charAt(i) != '0') {
                lastIndex = i;
                break;
            }
        }

        if (lastIndex == -1) {
            return "0000";
        }

        int startIndex = Math.max(lastIndex - 3, 0);
        String result = StrUtil.sub(numbers, startIndex, lastIndex + 1);

        return StrUtil.padPre(result, 4, '0');
    }

    @org.junit.Test
    public void test8() {
        System.out.println(IdUtil.fastUUID());
        System.out.println(IdUtil.fastSimpleUUID());
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);
        System.out.println(simpleUUID.substring(0, 16));
    }

    @org.junit.Test
    public void test9() {
        String message = ":15A:\n" +
                ":20:60964a462708401a\n" +
                ":22A:NEWT\n" +
                ":22C:\n" +
                ":82A:\n" +
                ":87A:hkd\n" +
                ":16B:\n" +
                ":30T:20240404\n" +
                ":30V:20240406\n" +
                ":36:7,1111\n" +
                ":32B:USD100\n" +
                ":57A:ICBKCNBJCCS\n" +
                ":33B:CNY711,11\n" +
                ":57A:BKCHCNBJAGT";
        List<MessageContent> result = parseMessage(message);
        result.forEach(System.out::println);
    }

    public static List<MessageContent> parseMessage(String message) {
        List<MessageContent> list = new ArrayList<>();
        String[] lines = StrUtil.splitToArray(message, C_LF);

        for (String line : lines) {
            if (StrUtil.startWith(line, C_COLON)) {
                int secondColonIndex = line.indexOf(C_COLON, 1);
                if (secondColonIndex != -1) {
                    String domain = line.substring(0, secondColonIndex + 1);
                    String value = line.substring(secondColonIndex + 1);
                    list.add(new MessageContent(domain, value));
                }
            }
        }
        return list;
    }

    @org.junit.Test
    public void test10() {
        System.out.println(IdUtil.fastUUID());
        System.out.println(IdUtil.fastSimpleUUID());
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);
        System.out.println(simpleUUID.substring(0, 16));
    }

    @org.junit.Test
    public void test11() {
        System.out.println(Iso8601Util.format(new Date()));
    }

    @org.junit.Test
    public void test12() {
        String isoTime = "2025-09-10T06:49:45.000Z";
        // UTC 时间
        System.out.println(getHHmm(isoTime, 0)); // 0649

        // 北京时间（UTC+8）
        System.out.println(getHHmm(isoTime, 8)); // 1449
    }

    /**
     * 从 ISO8601 字符串提取 HHmm（时分）
     *
     * @param isoTime    ISO8601 格式时间，例如 2025-09-10T06:49:45.000Z
     * @param offsetHour 时区偏移，例如东八区传 8
     * @return HHmm 格式的字符串
     */
    private static String getHHmm(String isoTime, int offsetHour) {
        // 解析 ISO8601 格式时间（默认按 UTC）
        DateTime dt = DateUtil.parse(isoTime);

        // 按时区偏移修正
        DateTime local = DateUtil.offsetHour(dt, offsetHour);

        // 格式化成 HHmm
        return DateUtil.format(local, "HHmm");
    }





}
