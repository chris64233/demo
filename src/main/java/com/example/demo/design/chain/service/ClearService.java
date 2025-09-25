package com.example.demo.design.chain.service;

import com.example.demo.dao.entity.ClearFlowConfig;
import com.example.demo.dao.service.ClearFlowConfigService;
import com.example.demo.design.chain.domain.Cashflow;
import com.example.demo.design.chain.handler.Handler;
import com.example.demo.design.chain.handler.HandlerChain;
import com.example.demo.design.chain.handler.HandlerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
@Service
@RequiredArgsConstructor
public class ClearService {

    private final HandlerRegistry handlerRegistry;
    private final ClearFlowConfigService clearFlowConfigService;

    public void clear(Cashflow cf) {
        String tradeType = cf.getTradeType();

        // 从数据库查询流程配置
        List<ClearFlowConfig> configList = clearFlowConfigService.getByTradeType(tradeType);
        if (configList.isEmpty()) {
            throw new RuntimeException("未知交易类型或未配置流程: " + tradeType);
        }

        // 根据配置生成 Handler 链
        List<Handler> handlerList = configList.stream().map(cfg -> {
            Handler h = handlerRegistry.getHandler(cfg.getHandlerName());
            if (h == null) {
                throw new RuntimeException("未注册的 Handler: " + cfg.getHandlerName());
            }
            return h;
        }).collect(Collectors.toList());

        // 执行责任链
        HandlerChain chain = new HandlerChain(handlerList);
        chain.next(cf);
    }
}
