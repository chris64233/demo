package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yangchi
 * @since 2024-10-29
 */
@Data
@Accessors(chain = true)
public class DoorInfoLog implements Serializable {

    /**
     * 设备编号
     */
    private transient String devId;

    /**
     * 仓门编号
     */
    private String doorId;

    /**
     * 电池信息
     */
    private transient BatteryInfoLog batteryInfoLog;

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
     * 柜体状态 1:电池正在充电 2:电池充满 5:异常 0:无电池
     */
    private Integer chargeStatus;
}