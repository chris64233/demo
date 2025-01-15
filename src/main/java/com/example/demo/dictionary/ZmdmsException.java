package com.example.demo.dictionary;


import com.example.demo.dictionary.enums.IResultCode;

/**
 * @author fands
 * @Title: ZmdmsException
 * @Package com.zmdms.common.exception
 * @Description: TODO
 * @date 2024/12/6 8:30
 * @Version 1.0.0
 */
public class ZmdmsException extends RuntimeException{
    Integer code;
    public ZmdmsException(IResultCode resultCode) {
        this(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public ZmdmsException(String msg) {
        super(msg);
    }

    public ZmdmsException(Integer code, String msg) {
        this(msg);
        this.code = code;
    }
    public ZmdmsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public Integer getCode() {
        return code;
    }
}
