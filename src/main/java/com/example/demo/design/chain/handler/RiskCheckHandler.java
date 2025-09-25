package com.example.demo.design.chain.handler;

import com.example.demo.design.chain.domain.Cashflow;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
@Component
public class RiskCheckHandler implements Handler {

    @Override
    public void handle(Cashflow cf, HandlerChain chain) {
        System.out.println("执行风控校验...");
        if (cf.getAmount() > 1000000) {
            throw new RuntimeException("风控拦截：金额过大！");
        }
        chain.next(cf);
    }

    @Override
    public String name() { return "RiskCheck"; }
}
