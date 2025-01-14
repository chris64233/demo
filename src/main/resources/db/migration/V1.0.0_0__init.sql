CREATE TABLE `pay_channel` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `english_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '渠道英文名称',
                               `chinese_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '渠道中文名称',
                               `encryp_type` tinyint DEFAULT '0' COMMENT '加密方式',
                               `status` int DEFAULT '0' COMMENT '状态',
                               `max_sub_mer` int DEFAULT NULL COMMENT '子商户最大限制',
                               `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 是',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                               `total_rate` decimal(10,6) DEFAULT NULL COMMENT '分账比例上限',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付渠道';
