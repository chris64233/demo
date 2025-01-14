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
* @since 2024-11-05
*/
@Data
@Accessors(chain = true)
@TableName("device_cabinet_last_info")
public class CabinetLastInfo implements Serializable {


    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 设备id
    */
    private String devId;

    /**
    * 仓门信息
    */
    private String boxInfo;

    /**
    * 柜子当前的电流
    */
    private String cabinetCurrent;

    /**
    * 柜子温度
    */
    private String cabinetTemp;

    /**
    * 整个柜子电压
    */
    private String cabinetVol;

    /**
    * 固件版本
    */
    private String hardVersion;

    /**
    * 充电口信息
    */
    private String portInfo;

    /**
    * sim 卡的iccid
    */
    private String simIccid;

    /**
    * 软件版本
    */
    private String softVersion;

    /**
    * 信号强度
    */
    private String cabinetRssi;

    /**
    * 柜子电表读数
    */
    private String electricMeter;

    /**
    * 工作电压
    */
    private String workVol;

    /**
    * 柜子经度
    */
    private String lon;

    /**
    * 柜子纬度
    */
    private String lat;

    /**
    * 设备上报时间
    */
    private Date deviceTime;

    /**
    * 是否全量上报 0:否 1:是
    */
    private Integer isFull;

    /**
    * 厂家code
    */
    private String makerCode;


}