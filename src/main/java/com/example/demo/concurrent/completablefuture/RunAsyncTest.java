package com.example.demo.concurrent.completablefuture;

import com.example.demo.util.SleepUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("start------------");
        CompletableFuture.runAsync(RunAsyncTest::testMethod);
        System.out.println("end------------");
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    private static void testMethod() {
        SleepUtils.sleep(3000);
        System.out.println("testMethod");
    }
}
