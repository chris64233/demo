package com.example.demo.design.strategy;

import com.example.demo.design.strategy.dto.SwiftMessage;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestStrategyImpl1 implements TestStrategy<SwiftMessage> {
    @Override
    public boolean isMatch(Integer type) {
        return Objects.equals(type, 1);
    }

    @Override
    public String doSomething(SwiftMessage message) {
        return "TestStrategyImpl1";
    }
}
