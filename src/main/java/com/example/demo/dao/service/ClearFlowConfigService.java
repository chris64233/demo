package com.example.demo.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dao.entity.ClearFlowConfig;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangchi
 * @since 2025-09-23
 */
public interface ClearFlowConfigService extends IService<ClearFlowConfig> {

    List<ClearFlowConfig> getByTradeType(String tradeType);

}
