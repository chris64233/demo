package com.example.demo.code_generator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商户对账流水明细
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_reconcile_detail")
public class ReconcileDetail implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对账结果ID
     */
    private String reconcileResultId;

    /**
     * 订单编号 退款订单号 和 支付订单号
     */
    private String orderNo;

    /**
     * 子订单号
     */
    private String subOrderNo;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 交易状态 1 已完成
     */
    private Integer tradeState;

    /**
     * 对账状态 -1 待进行 0 进行中 1 已对账
     */
    private Boolean reconcileState;

    /**
     * 对账结果 0 异常 1 正常
     */
    private Boolean reconcileResult;

    /**
     * 支付订单金额(元)
     */
    private BigDecimal orderAmt;

    /**
     * 支付交易金额(元)
     */
    private BigDecimal tradeAmt;

    /**
     * 支付手续费
     */
    private BigDecimal commFee;

    /**
     * 支付服务费
     */
    private BigDecimal serviceFee;

    /**
     * 支付优惠金额
     */
    private BigDecimal discountAmt;

    /**
     * 结算金额(元)
     */
    private BigDecimal settleAmt;

    /**
     * 支付通道 0 招商
     */
    private Integer payChannel;

    /**
     * 支付方式 0 微信小程序支付
     */
    private Integer payWay;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 结算状态 1 已结算
     */
    private String settleState;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 商户别名
     */
    private String merAlias;

    /**
     * 机构ID
     */
    private Long storeId;

    /**
     * 机构名
     */
    private String storeName;

    /**
     * 分账规则编号
     */
    private String ruleNo;


}
