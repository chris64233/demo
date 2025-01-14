package com.example.demo.task.once;

/**
 * @author fands
 * @Title: PayTaskLock
 * @Package com.zmdms
 * @Description: 支付任务分布式锁
 * @date 2024/12/16 14:03
 * @Version 1.0.0
 */
public enum PayTaskLock implements OnceTaskLock {

    PROFIT_SHARING("pay:profitsharing:lock", "pay:profitsharing:queue", 300L),
    CHECK_SHARING("pay:checksharing:lock", "pay:checksharing:queue", 0L),
    PAY_TIMEOUT("pay:timeout:lock", "pay:timeout:queue", 0L),
    PAY_RESULT("pay:check:order:lock", "pay:check:order:queue", 0L),
    REFUND_RETRY("pay:refund:try:lock", "pay:refund:try:queue", 10L),
    REFUND_CHECK("pay:refund:check:lock", "pay:refund:check:queue", 0L),
    ;

    String lockKey;
    String queueKey;
    Long waitTime;

    PayTaskLock(String lockKey, String queueKey, Long waitTime) {
        this.lockKey = lockKey;
        this.queueKey = queueKey;
        this.waitTime = waitTime;
    }

    @Override
    public String getLockKey() {
        return lockKey;
    }

    @Override
    public String getQueueKey() {
        return queueKey;
    }

    @Override
    public Long getWaitTime() {
        return waitTime;
    }


}
