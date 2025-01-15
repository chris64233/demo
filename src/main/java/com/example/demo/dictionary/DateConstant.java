package com.example.demo.dictionary;

public interface DateConstant {

    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    String YYYYMMDD = "yyyy-MM-dd";
    String YYYYMMDD2 = "yyyyMMdd";

    String YYYYMM = "yyyy-MM";

    String YYYY_MM_DD = YYYYMMDD;

    String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    String yyyyMMddHHmm = "yyyyMMddHHmm";

    String ZHyyyyMMdd = "yyyy年MM月dd日";

    String HH_MM_SS = "HH:mm:ss";

    /**默认的时间格式 */
    String DEFAULT_DATE_TIME_FORMAT = YYYY_MM_DD_HH_MM_SS;
    String DEFAULT_DATE_FORMAT = YYYYMMDD ;
    String DEFAULT_TIME_FORMAT = HH_MM_SS;

    String DEFAULT_TIME_ZONE = "GMT+8";
}
