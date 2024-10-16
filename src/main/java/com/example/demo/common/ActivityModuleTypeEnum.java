package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ActivityModuleTypeEnum {

    TITLE(1, "头部", ""),
    NFT(2, "NFT奖池模块", ""),
    MISSION_LIST(3, "任务中心-任务列表模块", ""),
    OPEN_NFT(4, "任务中心-NFT开盒模块", ""),
    TITLE_DESC(5, "富文本模块", ""),
    TITLE_IMG(6, "图片模块", ""),
    BASE(7, "基础模块", ""),
    ANNOUNCEMENT(8, "中奖公告栏", ""),
    NORMAL(9, "通用模块", ""),
    GROUP(10, "组模块", false, ""),
    JUMP_LINK(11, "跳转链接模块", ""),
    NFT_SALE(12, "NFT售卖模块", ""),
    SHARE(13, "分享模块", ""),
    TRADE_RANK(14, "交易排行榜", ""),
    QUESTION(15, "问题收集", ""),
    SHOW_DATA(16, "数据展示", ""),
    TURNTABLE(17, "大转盘抽奖", ""),
    INSCRIPTION_TIME_LINE(18, "铭文时间线", ""),
    INSCRIPTION_PROGRESS_BAR(19, "铭文进度条", ""),
    INSCRIPTION_MARKET(20, "铭文市场模块", ""),
    ;

    private Integer type;

    private String name;

    private boolean isSingle = true;

    private String description;

    ActivityModuleTypeEnum(Integer type, String name, boolean isSingle, String description) {
        this.type = type;
        this.name = name;
        this.isSingle = isSingle;
        this.description = description;
    }

    ActivityModuleTypeEnum(Integer type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public static ActivityModuleTypeEnum getByType(Integer type) {
        if (type == null) {
            return null;
        }

        for (ActivityModuleTypeEnum value : ActivityModuleTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }

        return null;
    }
}
