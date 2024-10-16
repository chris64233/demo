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
 * 支付渠道
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_channel")
public class Channel implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 渠道英文名称
     */
    private String englishName;

    /**
     * 渠道中文名称
     */
    private String chineseName;

    /**
     * 加密方式
     */
    private Integer encrypType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 子商户最大限制
     */
    private Integer maxSubMer;

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
