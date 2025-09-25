package com.example.demo.design.strategy;

import com.example.demo.design.strategy.dto.TestMessage;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestStrategyImpl2 implements TestStrategy<TestMessage> {
    @Override
    public boolean isMatch(Integer type) {
        return Objects.equals(type,2);
    }

    @Override
    public String doSomething(TestMessage message) {
        return "TestStrategyImpl2";
    }
}
