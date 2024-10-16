package com.example.demo.domain;

import com.example.demo.common.ActivityModuleTypeEnum;
import com.example.demo.common.ActivityTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ActivityBO implements Serializable {


    private Long id;

    private String        code;

    private String        name;

    private Long startTime;
    private Long endTime;

    private Integer       openStatus;

    private Integer       deleted;

    private Long          moduleId;

    private Integer       collectibleId;

    private BigDecimal nftAmount;

    private ActivityTypeEnum type;

    private List<ActivityModuleBO<?>> activityModules;

    public ActivityBO() {

    }


    public ActivityModuleBO<?> findByCode(String moduleCode) {
        if (StringUtils.isBlank(moduleCode)) {
            return null;
        }

        for (ActivityModuleBO<?> activityModule : activityModules) {
            if (activityModule.getCode().equals(moduleCode)) {
                return activityModule;
            }

            if (!CollectionUtils.isEmpty(activityModule.getSubModules())) {
                for (ActivityModuleBO<?> subModule : activityModule.getSubModules()) {
                    if (subModule.getCode().equals(moduleCode)) {
                        return subModule;
                    }
                }
            }
        }

        return null;
    }


    public List<ActivityModuleBO<?>> findByCodes(List<String> moduleCodes) {
        if (CollectionUtils.isEmpty(moduleCodes)) {
            return new ArrayList<>();
        }

        List<ActivityModuleBO<?>> modules = new ArrayList<>();
        for (ActivityModuleBO<?> activityModule : activityModules) {
            if (moduleCodes.contains(activityModule.getCode())) {
                modules.add(activityModule);
            }

            if (!CollectionUtils.isEmpty(activityModule.getSubModules())) {
                for (ActivityModuleBO<?> subModule : activityModule.getSubModules()) {
                    if (moduleCodes.contains(subModule.getCode())) {
                        modules.add(subModule);
                    }
                }
            }
        }

        return modules;
    }


    public ActivityModuleBO<?> findFirstByType(ActivityModuleTypeEnum type) {

        for (ActivityModuleBO<?> activityModule : activityModules) {
            if (activityModule.getType() == type) {
                return activityModule;
            }

            if (!CollectionUtils.isEmpty(activityModule.getSubModules())) {
                for (ActivityModuleBO<?> subModule : activityModule.getSubModules()) {
                    if (subModule.getType() == type) {
                        return subModule;
                    }
                }
            }
        }

        return null;
    }

    public List<ActivityModuleBO<?>> findByType(ActivityModuleTypeEnum type) {

        List<ActivityModuleBO<?>> list = new ArrayList<>();
        for (ActivityModuleBO<?> activityModule : activityModules) {
            if (activityModule.getType() == type) {
                list.add(activityModule);
            }

            if (!CollectionUtils.isEmpty(activityModule.getSubModules())) {
                for (ActivityModuleBO<?> subModule : activityModule.getSubModules()) {
                    if (subModule.getType() == type) {
                        list.add(subModule);
                    }
                }
            }
        }

        return list;
    }
}
