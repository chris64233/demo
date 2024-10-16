package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yangchi
 * @date 2024/6/18
 * <p>
 * Description: 电柜异常状态枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExStatusEnum {
    NORMAL(0, "正常"),
    OFFLINE(1, "离线"),
    FORBID(2, "禁用"),
    LOCKED(3, "锁电"),
    LOST(4, "丢失"),
    ABNORMAL(5, "异常开门");

    private Integer status;
    private String desc;

    public static String getDesc(Integer status) {

        for (ExStatusEnum e : ExStatusEnum.values()) {
            if (e.status.equals(status)) {
                return e.desc;
            }
        }
        return null;
    }

}
