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
 * 企业信息表
 * </p>
 *
 * @author yangchi
 * @since 2024-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_corp_info")
public class CorpInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 证件类型 1:营业执照 2:统一社会信用代码证
     */
    private Integer licenseType;

    /**
     * 证件号码
     */
    private String licenseNo;

    /**
     * 单位名称
     */
    private String corpName;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 证件有效期起
     */
    private Date licenseValidStart;

    /**
     * 证件有效期止
     */
    private Date licenseValidEnd;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 证件附件
     */
    private String licenseAttachFile;

    /**
     * 法人姓名
     */
    private String legalPerson;

    /**
     * 企业电话
     */
    private String telephone;

    /**
     * 法人身份证号
     */
    private String legalCertId;

    /**
     * 法人联系电话
     */
    private String legalPhone;

    /**
     * 身份证有效期起
     */
    private Date certValidStart;

    /**
     * 身份证有效期止
     */
    private Date certValidEnd;

    /**
     * 身份证附件
     */
    private String certAttachFile;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
