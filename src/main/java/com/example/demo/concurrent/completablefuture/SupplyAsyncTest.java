package com.example.demo.concurrent.completablefuture;

import com.example.demo.util.SleepUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("start------------");
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return testMethod();
        });
        System.out.println("end------------");
        System.out.println("cost: " + (System.currentTimeMillis() - start));
        String s = uCompletableFuture.get();
        System.out.println(s);
    }

    private static String testMethod() {
        SleepUtils.sleep(3000);
        System.out.println("testMethod");
        return "async testMethod result";
    }
}
