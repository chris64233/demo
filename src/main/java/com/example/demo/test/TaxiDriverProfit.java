package com.example.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaxiDriverProfit {

    public static long taxiDriver(List<Long> pickup, List<Long> drop, List<Integer> tip) {
        // 验证输入
        if (pickup == null || drop == null || tip == null ||
                pickup.size() != drop.size() || drop.size() != tip.size()) {
            return 0L;
        }

        int n = pickup.size();
        if (n == 0) return 0L;

        // 创建订单列表并按照下车位置排序
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long profit = drop.get(i) - pickup.get(i) + tip.get(i);
            orders.add(new Order(pickup.get(i), drop.get(i), profit));
        }
        orders.sort(Comparator.comparingLong(o -> o.dropoff));

        // dp数组，dp[i]表示前i个订单的最大利润
        long[] dp = new long[n + 1];
        dp[0] = 0L; // 没有订单时利润为0

        for (int i = 1; i <= n; i++) {
            Order current = orders.get(i - 1);
            long profit = current.profit;

            // 找到最后一个不与当前订单冲突的订单
            int lastCompatible = findLastCompatible(orders, i - 1);

            // 状态转移：接或不接当前订单
            dp[i] = Math.max(dp[i - 1], profit + (lastCompatible == -1 ? 0L : dp[lastCompatible + 1]));
        }

        return dp[n];
    }

    // 使用二分查找找到最后一个不与当前订单冲突的订单
    private static int findLastCompatible(List<Order> orders, int currentIndex) {
        Order current = orders.get(currentIndex);
        int left = 0, right = currentIndex - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (orders.get(mid).dropoff <= current.pickup) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 订单内部类
    static class Order {
        long pickup;
        long dropoff;
        long profit;

        public Order(long pickup, long dropoff, long profit) {
            this.pickup = pickup;
            this.dropoff = dropoff;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        // 测试用例
        List<Long> pickup = Arrays.asList(1L, 3L, 0L, 5L, 8L);
        List<Long> drop = Arrays.asList(4L, 5L, 6L, 7L, 9L);
        List<Integer> tip = Arrays.asList(50, 20, 100, 60, 40);

        System.out.println(taxiDriver(pickup, drop, tip)); // 输出应为150 (50+60+40)
    }
}
