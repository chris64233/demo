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
@TableName("device_receive_packet")
public class ReceivePacket implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 设备SN
    */
    private String ueSn;

    /**
    * 上报时间
    */
    private Date reportTime;

    /**
    * 指令类型 110-登录  310-属性  410-告警
    */
    private Integer packetType;

    /**
    * 指令
    */
    private String packet;

    /**
    * 回复指令
    */
    private String responsePacket;

    private Date createTime;


}