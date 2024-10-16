package com.example.demo.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Test {
    @RequestMapping("/test")
    public String hello() {
        return "hello world";
    }
}
