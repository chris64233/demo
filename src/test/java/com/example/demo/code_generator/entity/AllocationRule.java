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
 * 分账规则
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_allocation_rule")
public class AllocationRule implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分账规则编号
     */
    private String ruleNo;

    /**
     * 分账规则名称
     */
    private String ruleName;

    /**
     * 分账类型
     */
    private Integer ruleType;

    /**
     * 分账比例上限
     */
    private BigDecimal totalRate;

    /**
     * 启用状态 0 启用 1 禁用
     */
    private Integer status;

    /**
     * 是否删除 0 否 1 是
     */
    private Integer deleted;

    /**
     * 分账商户号
     */
    private String merNo;

    /**
     * 规则生效日期
     */
    private Date effectiveDate;

    /**
     * 规则失效日期
     */
    private Date ineffectiveDate;

    /**
     * 规则状态 0:待生效 1:生效中 2:已作废 3:已过期
     */
    private Integer state;

    /**
     * 手续费分摊方式 1:交易金额分摊 2:指定商户承担 3:分账商户承担
     */
    private Integer commAllocationType;

    /**
     * 押金商户号
     */
    private String depositMerNo;

    /**
     * 平台服务费率
     */
    private BigDecimal platRate;

    /**
     * 分账接收信息
     */
    private String ruleMsg;

    /**
     * 备注
     */
    private String comment;

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
