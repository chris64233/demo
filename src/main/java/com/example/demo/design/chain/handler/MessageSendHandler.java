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
public class MessageSendHandler implements Handler {

    @Override
    public void handle(Cashflow cf, HandlerChain chain) {
        System.out.println("发送清算完成消息...");
        chain.next(cf);
    }

    @Override
    public String name() { return "MessageSend"; }
}
