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
* @since 2024-10-30
*/
@Data
@Accessors(chain = true)
@TableName("device_battery_info_log")
public class BatteryInfoLog implements Serializable {


    /**
    * 主键 (dev_id+'_'+box_sn+'_'+timestampt)
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 设备编号
    */
    private String devId;

    /**
    * 电池的充电电流
    */
    private String cur;

    /**
    * 电池电压
    */
    private String vol;

    /**
    * 电芯温度
    */
    private String cellTemp;

    /**
    * 环境温度
    */
    private String ambientTemp;

    /**
    * 卡板温度
    */
    private String cardBoardTemp;

    /**
    * 单体电压
    */
    private String singleVol;

    /**
    * 电池条码
    */
    private String sn;

    /**
    * 电池电量百分比
    */
    private Integer soc;

    /**
    * 电池健康百分比
    */
    private Integer soh;

    /**
    * 仓门编号
    */
    private Integer boxSn;

    /**
    * 充电器电压
    */
    private String dcVol;

    /**
    * 充电器电流
    */
    private String dcCur;

    /**
    * 数据上报时间
    */
    private Date deviceTime;


}