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
 * 渠道套餐规则关系表
 * </p>
 *
 * @author yangchi
 * @since 2024-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("o_package_rule_relation")
public class PackageRuleRelation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 渠道套餐ID
     */
    private Long storePackageId;

    /**
     * 分账规则编号
     */
    private String ruleNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    /**
     * 修改者
     */
    private Long modifier;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 是否删除 0:否 1:是
     */
    private Integer deleted;


}
