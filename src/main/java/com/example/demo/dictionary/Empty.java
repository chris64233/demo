package com.example.demo.dictionary;

/**
 * @author fands
 * @Title: Empty
 * @Package com.zmdms.common.jackson
 * @Description: 默认值
 * @date 2024/5/10 14:55
 * @Version 1.0.0
 */
public enum Empty implements DicCommonEnum{
    ;

    @Override
    public String getText(String code) {
        return null;
    }
}
