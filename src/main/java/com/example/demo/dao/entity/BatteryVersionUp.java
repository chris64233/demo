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
* @since 2024-10-28
*/
@Data
@Accessors(chain = true)
@TableName("t_battery_version_up")
public class BatteryVersionUp implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 设备SN
    */
    private String ueSn;

    /**
    * 升级方式, 1-立即升级 2-预约升级
    */
    private Integer upType;

    /**
    * 硬件类型, 1-DTU 2-BMS
    */
    private Integer hardType;

    /**
    * 升级版本号
    */
    private String version;

    /**
    * 现在版本号
    */
    private String oldVersion;

    /**
    * 版本下载地址
    */
    private String url;

    /**
    * 通知时间
    */
    private Date noticeTime;

    /**
    * 状态 0-未升级 1-已升级
    */
    private Integer status;

    /**
    * 创建人
    */
    private String creator;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;


}