package com.example.demo.util;

import org.apache.commons.lang3.RandomStringUtils;

public abstract class SaltUtil {

    static char[] chars = new char[]{'1', '2', '3', '4','5', '6', '7', '8', '9', '0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    static char[] number = new char[]{'2', '3', '4', '5', '6'};

    // 生成:32位-36位随机盐值
    public static String generateSalt32_36(){
        int count = Integer.valueOf("3" + RandomStringUtils.random(1, 0, 5, false, false, number));
        return RandomStringUtils.random(count,0, chars.length,false, false, chars);
    }

    // 生成:6位随机盐值
    public static String generateSalt6(){
        return RandomStringUtils.random(6,0, chars.length,false, false, chars);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println( i + " :" + generateSalt32_36());
        }


    }
}
