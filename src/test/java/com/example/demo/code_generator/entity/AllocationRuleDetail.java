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
 * 分账规则详情
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_allocation_rule_detail")
public class AllocationRuleDetail implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分账规则编号
     */
    private String ruleNo;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 分账比例（%）
     */
    private BigDecimal rate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 启用状态 0 启用 1禁用
     */
    private Integer status;

    /**
     * 是否删除 0 否 1是
     */
    private Integer deleted;

    /**
     * 分账方式 0:比例 1:固定金额
     */
    private Integer type;

    /**
     * 分账金额（元）
     */
    private BigDecimal amount;

    /**
     * 手续费承担方 0:否 1:是
     */
    private Integer isComm;

    /**
     * 排序号
     */
    private Integer sortNum;


}
