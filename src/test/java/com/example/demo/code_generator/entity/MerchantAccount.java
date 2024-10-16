package com.example.demo.code_generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商户账户
 * </p>
 *
 * @author yangchi
 * @since 2024-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_merchant_account")
public class MerchantAccount implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 商户全称
     */
    private String merName;

    /**
     * 支付平台 0:招行一体化收单
     */
    private Integer channel;

    /**
     * 商户门店号
     */
    private String merStoreNo;

    /**
     * 商户用途，0 主账户 1 押金账户 2 子账户
     */
    private Integer merPurpose;

    /**
     * 商户类型
     */
    private String merType;

    /**
     * 状态 0:启用 1:停用 2:暂存
     */
    private Integer status;

    /**
     * 是否删除 0 否 1 是
     */
    private Integer deleted;

    /**
     * 商户别名
     */
    private String merAlias;

    /**
     * 商户层级 0:普通商户 1:平台商户
     */
    private Integer merTier;

    /**
     * 关联系统机构ID
     */
    private Long instId;

    /**
     * 关联系统机构类型 0:机构 1:资金方
     */
    private Integer instType;

    /**
     * 所属平台商户
     */
    private String platMerNo;

    /**
     * 商户类别 0:企业 1:个人
     */
    private Integer merCategory;

    /**
     * 商户联系电话
     */
    private String merContactInfo;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人手机号
     */
    private String contactsPhone;

    /**
     * 是否接受押金 0:否 1:是
     */
    private Integer isDeposit;

    /**
     * 备注
     */
    private String comment;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
