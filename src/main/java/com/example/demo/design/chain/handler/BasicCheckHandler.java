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
public class BasicCheckHandler implements Handler {

    @Override
    public void handle(Cashflow cf, HandlerChain chain) {
        System.out.println("执行基础校验...");
        chain.next(cf);
    }

    @Override
    public String name() { return "BasicCheck"; }
}
