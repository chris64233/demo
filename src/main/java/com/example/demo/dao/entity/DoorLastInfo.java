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
@TableName("device_door_last_info")
public class DoorLastInfo implements Serializable {


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
    * 仓门编号
    */
    private Integer boxSn;

    /**
    * 柜门状态 0:关 1:开
    */
    private Integer status;

    /**
    * 是否禁用 0:禁用 1:启用
    */
    private Integer enableStatus;

    /**
    * 单仓温度
    */
    private String temp;

    /**
    * 充电状态 1:电池正在充电 2:电池充满 5:异常 0:无电池
    */
    private Integer chargeStatus;

    /**
    * 电池编号
    */
    private String batterySn;

    /**
    * 设备上报时间
    */
    private Date deviceTime;


}