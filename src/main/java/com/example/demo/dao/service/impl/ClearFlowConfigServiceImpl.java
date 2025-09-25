package com.example.demo.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.entity.ClearFlowConfig;
import com.example.demo.dao.mapper.ClearFlowConfigMapper;
import com.example.demo.dao.service.ClearFlowConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangchi
 * @since 2025-09-23
 */
@Service
public class ClearFlowConfigServiceImpl extends ServiceImpl<ClearFlowConfigMapper, ClearFlowConfig> implements ClearFlowConfigService {

    @Resource
    private ClearFlowConfigMapper clearFlowConfigMapper;

    @Override
    public List<ClearFlowConfig> getByTradeType(String tradeType) {
        LambdaQueryWrapper<ClearFlowConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClearFlowConfig::getTradeType, tradeType)
                .orderByAsc(ClearFlowConfig::getSeq);

        return clearFlowConfigMapper.selectList(wrapper);
    }
}
