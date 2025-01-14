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
* @since 2025-01-03
*/
@Data
@Accessors(chain = true)
@TableName("u_maintainer_cabinet_setting")
public class MaintainerCabinetSetting implements Serializable {


    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer userId;

    /**
    * 批量开仓时段
    */
    private String openingPeriod;

    /**
    * 开仓次数限制/日
    */
    private Integer dailyOpenTime;

    /**
    * 今日开仓次数
    */
    private Integer openTimeToday;

    /**
    * 创建人
    */
    private Long creator;

    /**
    * 修改人
    */
    private Long modifier;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date modifyTime;


}