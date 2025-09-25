package com.example.demo.design.strategy;

import com.example.demo.design.strategy.dto.BaseMessage;

public interface TestStrategy<T extends BaseMessage> {

    boolean isMatch(Integer type);

    String doSomething(T message);
}
