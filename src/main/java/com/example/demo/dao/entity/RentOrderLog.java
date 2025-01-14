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
* @since 2024-12-26
*/
@Data
@Accessors(chain = true)
@TableName("o_rent_order_log")
public class RentOrderLog implements Serializable {


    /**
    * ID
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
    * 订单ID
    */
    private Long orderId;

    /**
    * 事件名称
    */
    private String event;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人
    */
    private Long creator;


}