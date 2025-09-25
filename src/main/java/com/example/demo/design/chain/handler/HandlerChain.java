package com.example.demo.design.chain.handler;

import com.example.demo.design.chain.domain.Cashflow;

import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
public class HandlerChain {

    private final Iterator<Handler> iterator;

    public HandlerChain(List<Handler> handlers) {
        this.iterator = handlers.iterator();
    }

    public void next(Cashflow cf) {
        if (iterator.hasNext()) {
            Handler handler = iterator.next();
            handler.handle(cf, this);
        }
    }
}
