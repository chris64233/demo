package com.example.demo.test;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(getMaxProfit(Arrays.asList(15, -3, -2, -1), 1));
    }

    public static long getMaxProfit(List<Integer> pnl, int k) {
        // 创建正数和负数的列表
        List<Integer> profits = new ArrayList<>();
        List<Integer> losses = new ArrayList<>();

        // 遍历 pnl，分离正数和负数
        for (int val : pnl) {
            if (val >= 0) {
                profits.add(val);  // 添加到 profits 列表
            } else {
                losses.add(val);  // 添加到 losses 列表
            }
        }

        // 如果负数的数量小于 k，返回 -1
        if (losses.size() < k) {
            return -1;
        }

        // 计算所有正数的总利润
        long totalProfit = 0;
        for (int profit : profits) {
            totalProfit += profit;
        }

        // 排序负数列表，选出前 k 个亏损最小的负数
        losses.sort(Comparator.comparingInt(Math::abs));

        // 选择前 k 个负数，加入总利润
        for (int i = 0; i < k; i++) {
            totalProfit += losses.get(i);  // 将亏损加到总利润中
        }

        // 如果总利润为负，返回 -1
        if (totalProfit < 0) {
            return -1;
        }

        // 返回最大利润
        return totalProfit;
    }

}
