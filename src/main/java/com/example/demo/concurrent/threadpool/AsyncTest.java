package com.example.demo.concurrent.threadpool;

import com.example.demo.util.SleepUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {
    @Async("doubleTaskExecutor")
    public void async() {
        SleepUtils.sleep(3000);
        System.out.println("async executed by : " + Thread.currentThread().getName());
    }
}
