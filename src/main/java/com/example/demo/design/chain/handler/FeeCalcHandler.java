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
public class FeeCalcHandler implements Handler {

    @Override
    public void handle(Cashflow cf, HandlerChain chain) {
        System.out.println("计算手续费...");
        cf.setFee(cf.getAmount() * 0.001);
        System.out.println("手续费=" + cf.getFee());
        chain.next(cf);
    }

    @Override
    public String name() { return "FeeCalc"; }
}
