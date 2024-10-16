package com.example.demo.concurrent.threadpool;

import com.example.demo.util.SleepUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/threadpool")
public class ThreadPoolTest {

    @Resource(name = "doubleTaskExecutor")
    private ThreadPoolTaskExecutor doubleTaskExecutor;

    @Resource
    private AsyncTest asyncTest;

    @RequestMapping("/test")
    public void threadpool() {
        long start = System.currentTimeMillis();
        System.out.println("start------------");
        CompletableFuture.runAsync(this::testMethod, doubleTaskExecutor);
        System.out.println("end------------");
        System.out.println("cost: " + (System.currentTimeMillis() - start) + ", executed by " + Thread.currentThread().getName());
    }

    private void testMethod() {
        SleepUtils.sleep(3000);
        System.out.println("testMethod executed by : " + Thread.currentThread().getName());
    }

    /**
     * 异步测试生效
     */
    @RequestMapping("/test2")
    public void threadpool2() {
        long start = System.currentTimeMillis();
        System.out.println("start------------");
        asyncTest.async();
        System.out.println("end------------");
        System.out.println("cost: " + (System.currentTimeMillis() - start) + ", executed by " + Thread.currentThread().getName());
    }

}
