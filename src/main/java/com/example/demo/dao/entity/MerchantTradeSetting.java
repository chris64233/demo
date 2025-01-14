package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author yangchi
* @since 2024-12-10
*/
@Data
@Accessors(chain = true)
@TableName("pay_merchant_trade_setting")
public class MerchantTradeSetting implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 商户号
    */
    private String merNo;

    /**
    * APP_ID
    */
    private String appId;

    /**
    * 应用名称
    */
    private String appName;

    /**
    * APP_SECRET
    */
    private String appSecret;

    /**
    * 公钥
    */
    private String publicKey;

    /**
    * 私钥
    */
    private String privateKey;

    /**
    * 收银员
    */
    private String cashier;

    /**
    * 微信AppID
    */
    private String wechatAppId;

    /**
    * 微信小程序支付费率(不大于1000‰，保留1位小数)
    */
    private Double wechatMiniFee;

    /**
    * 微信APP支付费率(不大于1000‰，保留1位小数)
    */
    private Double wechatAppFee;

    /**
    * 微信公众号支付费率(不大于1000‰，保留1位小数)
    */
    private Double wechatOfficialFee;

    /**
    * 微信线下扫码支付费率(不大于1000‰，保留1位小数)
    */
    private Double wechatScanFee;

    /**
    * 银联二维码支付费率(不大于1000‰，保留1位小数)
    */
    private Double unionQrFee;

    /**
    * 银联线上支付费率(不大于1000‰，保留1位小数)
    */
    private Double unionOnlineFee;

    /**
    * 是否删除 0 否 1 是
    */
    private Integer deleted;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date modifyTime;


}