package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ActivityTypeEnum {

    PLATFORM(1, "平台"),
    CONTRACT(2, "合约"),
    INSCRIPTION(3, "铭文"),

    ;

    private Integer type;

    private String description;
    ActivityTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }


    public static ActivityTypeEnum getByType(Integer type) {
        if (type == null) {
            return null;
        }

        for (ActivityTypeEnum value : ActivityTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }

        return null;
    }
}
