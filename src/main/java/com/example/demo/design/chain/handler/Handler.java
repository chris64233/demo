package com.example.demo.design.chain.handler;

import com.example.demo.design.chain.domain.Cashflow;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
public interface Handler {

    void handle(Cashflow cf, HandlerChain chain);

    String name(); // 每个处理器的名字，方便配置映射

}

