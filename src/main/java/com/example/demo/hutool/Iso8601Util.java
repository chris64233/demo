package com.example.demo.hutool;

import cn.hutool.core.date.format.FastDateFormat;

import java.util.Date;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/6/24
 */
public class Iso8601Util {
    private static final FastDateFormat ISO_8601_WITH_TZ =
            FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssXXX", TimeZone.getTimeZone("GMT+08:00"));

    public static String format(Date date) {
        return ISO_8601_WITH_TZ.format(date);
    }
}

