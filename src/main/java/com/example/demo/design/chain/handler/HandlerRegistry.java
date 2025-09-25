package com.example.demo.design.chain.handler;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
@Component
public class HandlerRegistry {

    private final Map<String, Handler> registry;

    public HandlerRegistry(List<Handler> handlers) {
        this.registry = handlers.stream()
                .collect(Collectors.toMap(Handler::name, h -> h));
    }

    public Handler getHandler(String name) {
        return registry.get(name);
    }

    public Map<String, Handler> getAll() {
        return registry;
    }
}
