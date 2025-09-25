package com.example.demo.design.strategy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestMessage extends BaseMessage {

    /**
     * 原MT300发报编号
     */
    private String originalReferenceNumber;

    /**
     * 共同编号
     */
    private String commonReference;

    /**
     * 甲方银行
     */
    @NotBlank(message = "甲方银行不能为空")
    private String senderBank;

    /**
     * 乙方银行
     */
    @NotBlank(message = "乙方银行不能为空")
    private String receiverBank;

    /**
     * 起息日
     */
    @NotNull(message = "起息日不能为空")
    private Date valueDate;

    /**
     * 交易汇率
     */
    @NotNull(message = "交易汇率不能为空")
    private BigDecimal exchangeRate;

    /**
     * 甲方买入货币
     */
    @NotBlank(message = "甲方买入货币不能为空")
    private String tradeCurrency;

    /**
     * 甲方买入金额
     */
    @NotNull(message = "甲方买入金额不能为空")
    private BigDecimal tradeAmount;

    /**
     * 甲方买入货币及金额
     */
    private String tradeCurrencyAmount;

    /**
     * 中间行
     */
    private String intermediaryBank;

    /**
     * 代理行
     */
    @NotBlank(message = "代理行不能为空")
    private String proxyBank;

    /**
     * 收款代理信息
     */
    private String receiverProxyInfo;

    /**
     * 甲方卖出货币
     */
    @NotBlank(message = "甲方卖出货币不能为空")
    private String counterCurrency;

    /**
     * 甲方卖出金额
     */
    @NotNull(message = "甲方卖出金额不能为空")
    private BigDecimal counterAmount;

    /**
     * 甲方卖出货币及金额
     */
    private String counterCurrencyAmount;

    /**
     * 是否修改或取消
     */
    private Boolean isModified;

    /**
     * 操作类型
     */
    private String indicator;

}
