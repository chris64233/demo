package com.example.demo.code_generator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支付订单表
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_trade_payment_order")
public class TradePaymentOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      private Long id;

    /**
     * 支付流水号
     */
    private String trxNo;

    /**
     * 第三方支付平台返回的支付单号
     */
    private String thirdOrderId;

    /**
     * 交易业务类型  ：消费、充值等
     */
    private String trxType;

    /**
     * 商户订单号 o_charge_product_order的主键id
     */
    private String merchantOrderNo;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    /**
     * 服务费率
     */
    private BigDecimal serviceRate;

    /**
     * 锁定余额金额
     */
    private BigDecimal lockBalance;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 状态(参考枚举:orderstatusenum)
     */
    private String status;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 商品名称
     */
    private String productCode;

    /**
     * 订单来源
     */
    private String orderFrom;

    /**
     * 支付方式编号
     */
    private String payWayCode;

    /**
     * 支付方式名称
     */
    private String payWayName;

    /**
     * 支付备注
     */
    private String remark;

    /**
     * 支付类型编号
     */
    private String payTypeCode;

    /**
     * 支付类型名称
     */
    private String payTypeName;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商家名称
     */
    private String accountNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 下单日期
     */
    private Date orderDate;

    /**
     * 下单ip(客户端ip,在网关页面获取)
     */
    private String orderIp;

    /**
     * 从哪个页面链接过来的(可用于防诈骗)
     */
    private String orderRefererUrl;

    /**
     * 页面回调通知url
     */
    private String returnUrl;

    /**
     * 后台异步通知url
     */
    private String notifyUrl;

    /**
     * 订单撤销原因
     */
    private String cancelReason;

    /**
     * 订单有效期(单位分钟)
     */
    private Integer orderPeriod;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 资金流入类型
     */
    private String fundIntoType;

    /**
     * 是否退款(100:是,101:否,默认值为:101)
     */
    private String isRefund;

    /**
     * 退款次数(默认值为:0)
     */
    private Integer refundTimes;

    /**
     * 成功退款总金额
     */
    private BigDecimal successRefundAmount;

    /**
     * 支付通道ID
     */
    private Long payChannelId;

    /**
     * 订单每日结算ID
     */
    private String settleRecordId;

    /**
     * 产品促销规则ID
     */
    private String salesPromotionId;

    /**
     * 产品订单ID
     */
    private String orderId;

    /**
     * 租赁商ID
     */
    private Long adminId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    /**
     * 分账规则ID
     */
    private Long alloRuleId;

    /**
     * 分账规则名
     */
    private String alloRuleName;

    /**
     * 失败原因
     */
    private String errMsg;

    /**
     * 完成时间
     */
    private Date completeTime;


}
