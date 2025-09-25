package com.example.demo.design.strategy;

import com.example.demo.design.strategy.dto.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/strategy")
public class StrategyTest {

    @Autowired
    private List<TestStrategy> testStrategies;

    @PostMapping("/test")
    public String hello(BaseMessage message) {
        TestStrategy testStrategy = testStrategies.stream().filter(u -> u.isMatch(message.getType())).findFirst().orElse(null);
        if (testStrategy == null) {
            return null;
        }
        return testStrategy.doSomething(message);
    }
}
