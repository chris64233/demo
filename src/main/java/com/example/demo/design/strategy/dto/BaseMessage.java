package com.example.demo.design.strategy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseMessage implements Serializable {

    /**
     * 支付渠道
     */
    @NotBlank(message = "支付渠道不能为空")
    private String channel;

    /**
     * 报文类型
     */
    @NotNull(message = "报文类型不能为空")
    private Integer type;

    /**
     * 发报编号
     */
    @NotBlank(message = "发报编号不能为空")
    private String referenceNumber;

    /**
     * 交易编号
     */
    @NotBlank(message = "交易编号不能为空")
    private String tradeNo;

    /**
     * 交易日
     */
    @NotNull(message = "交易日不能为空")
    private Date tradeDate;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易子类型
     */
    private String tradeSubtype;

    /**
     * 是否取消 1:是 0:否
     */
    @NotNull(message = "是否取消不能为空")
    private Integer isCancel;
}
