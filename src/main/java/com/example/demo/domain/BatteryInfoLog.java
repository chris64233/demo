package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* @author yangchi
* @since 2024-10-29
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
     * 总放电
     */
    private String totalDischarge;

    /**
     * 总充电
     */
    private String totalCharge;

    /**
     * 电池状态 0：移动 1：静止 2：存储 3：休眠
     */
    private Integer batteryStatus;

    /**
    * 数据上报时间
    */
    private Date deviceTime;


}