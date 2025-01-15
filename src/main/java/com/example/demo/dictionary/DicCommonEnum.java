package com.example.demo.dictionary;

/**
 * @author fands
 * @Title: DicCommonEnum
 * @Package com.zmdms.common.jackson
 * @Description: 实现该接口，可以实现接口返回数据的字典序列化
 * @date 2024/5/10 13:55
 * @Version 1.0.0
 */
public interface DicCommonEnum {

    /**
     * 根据编码获取实际字典值
     * @param code 编码值
     * @return
     */
    String getText(String code);
}
