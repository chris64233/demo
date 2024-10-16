package com.example.demo.domain;

import com.example.demo.common.ActivityModuleTypeEnum;
import com.example.demo.util.JsonUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
public class ActivityModuleBO<T> implements Serializable {



    private String activityCode;

    private String code;

    private String language;

    private ActivityModuleTypeEnum type;

    /**
     * 排序,同组的序号相同;可以用sort来判断是否是组
     */
    private Integer sort;

    private Integer x;

    private Integer y;

    private Integer w;

    private Integer h;

    private T ext;

    private List<ActivityModuleBO<?>> subModules;

    private Long moduleId;

}
