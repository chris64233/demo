package com.example.demo.dictionary.enums;

/**
 * @author fands
 * @Title: IResultCode
 * @Package com.zmdms.common.enums
 * @Description: 业务代码接口
 * @date 2024/4/2516:53
 * @Version 1.0.0
 */
public interface IResultCode {
    /**
     * 获取消息
     */
    String getMessage();

    /**
     * 获取状态码
     */
    int getCode();
}
