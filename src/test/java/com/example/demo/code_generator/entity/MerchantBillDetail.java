package com.example.demo.code_generator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商户子单流水
 * </p>
 *
 * @author yangchi
 * @since 2024-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_merchant_bill_detail")
public class MerchantBillDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 银行流水号
     */
      private String bankTrxNo;

    /**
     * 平台订单号
     */
    private String platOrderNo;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;

    /**
     * 手续费
     */
    private BigDecimal commFee;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmt;

    /**
     * 结算金额
     */
    private BigDecimal settleAmt;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 交易状态  -1:已取消 0:待完成 1:已完成 2:交易失败
     */
    private Integer status;

    /**
     * 结算状态
     */
    private String txnStatus;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 母单银行流水号
     */
    private String mainBankTrxNo;

    /**
     * 母单平台订单号
     */
    private String mainPlatOrderNo;

    /**
     * 对账状态 -1未对账 0 对账中 1 已对账
     */
    private Boolean reconcileFlag;


}
