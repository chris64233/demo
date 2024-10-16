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
 * 银行账号信息表
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_bank_account")
public class BankAccount implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 银行账户
     */
    private String accountNo;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户类型 0:对公 1:个人
     */
    private Integer accountType;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 联行号
     */
    private Long interbankNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
