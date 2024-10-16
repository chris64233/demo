package com.example.demo.code_generator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商户对账结果信息表
 * </p>
 *
 * @author yangchi
 * @since 2024-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_reconcile_result")
public class ReconcileResult implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID 对账日期 + 商户号
     */
      private String id;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 商户别名
     */
    private String merAlias;

    /**
     * 对账日期：yyyy-MM-dd
     */
    private String reconcileDate;

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
     * 退款订单金额(元)
     */
    private BigDecimal refundAmt;

    /**
     * 退款金额(元)
     */
    private BigDecimal refundTradeAmt;

    /**
     * 手续费退费金额(元)
     */
    private BigDecimal refundCommFee;

    /**
     * 服务费退费金额(元)
     */
    private BigDecimal refundServiceFee;

    /**
     * 总结算金额(元)
     */
    private BigDecimal settleAmt;

    /**
     * 支付通道 0 招商
     */
    private Integer payChannel;

    /**
     * 关联系统机构ID
     */
    private Long storeId;

    /**
     * 关联系统机构名
     */
    private String storeName;

    /**
     * 异常数量
     */
    private Integer errNum;


}
